Turmas
================ 

Este documento descreve o projecto da cadeira de Sistemas Distribuídos 2021/2022.

1 Introdução
------------

O objetivo do projeto de Sistemas Distribuídos (SD) é desenvolver o sistema **Turmas**, um serviço de inscrição em
turmas de uma dada unidade curricular. O serviço é fornecido por servidores através de chamadas a procedimentos remotos.
Para simplificar o projecto, assume-se que existe uma única turma.

O serviço pode ser acedido por três tipos de clientes: i) os *docentes*, que podem abrir e fechar inscrições, assim como
consultar e alterar o estado das inscrições; ii) os *alunos* que podem fazer consultas e inscrições; iii) os *
administradores* que mantêm o serviço em funcionamento.

O sistema será concretizado através de um conjunto de serviços gRPC implementados na plataforma Java.

O projecto está estruturado em três fases.


2 Arquitectura do sistema
------------------------

O sistema usa uma arquitectura cliente-servidor. O serviço é fornecido por um ou mais servidores que podem ser
contactados por processos cliente, através de chamadas a procedimentos remotos. Podem existir diferentes tipos de
clientes, que são usados por docentes, alunos, e administradores, respectivamente.

Em cada fase do projecto será explorada uma variante desta arquitectura básica, tal como se descreve abaixo.


2.1 Fase 1
-------------------

Nesta fase o serviço é prestado por um único servidor, que aceita pedidos no endereço/porto que é conhecido de antemão
por todos os clientes.

2.2 Fase 2
-------------------

Na segunda fase, o serviço é fornecido por dois servidores: um primário e um secundário. Todas as operações que alterem
o estado do sistema (que passaremos a designar como *operações de escrita*) só podem ser realizadas no primário, que
propaga as mesmas para o secundário. Operações que não alterem o estado do sistema (que passaremos a designar como *
operações de leitura*), podem ser feitas em qualquer um dos servidores. Os servidores comunicam entre si utilizando o
protocolo de **Gossip** (descrito mais à frente).

Para além disso, nesta fase os clientes não sabem à partida os endereços destes servidores, tendo por isso de recorrer a
um servidor de nomes **(a ser desenvolvido nas aulas teórico-práticas)** para obter esta informação.


2.3 Fase 3
-------------------

Na última fase, o serviço é fornecido por dois servidores que partilham estado usando um modelo de coerência eventual,
com capacidade de integrarem operações concorrentes. As operações de inscrição podem ser feitas em qualquer um dos
servidores, que propagam em diferido ("background") as alterações para o outro servidor. Sempre que possível, operações
executadas de forma concorrente são integradas num estado comum. Neste modelo algumas operações podem vir a ser
posteriormente canceladas de forma automática pelo sistema. Por exemplo, se dois alunos reservam a última vaga numa
turma, de forma concorrente, usando servidores diferentes.

Mais concretamente, considere-se uma turma com capacidade para 2 alunos e que inicialmente está vazia. Considere dois
alunos que contactam concorrentemente dois servidores distintos para fazer uma inscrição:

    - O aluno A faz a sua inscrição no servidor S1
    - O aluno B faz a sua inscrição no servidor S2

Temporariamente, os servidores vão ter informação incoerente: cada um deles tem apenas uma inscrição nesta turma, mas de
um aluno diferente. Após a propagação diferida das actualizações entre os servidores, ambos os servidores devem ficar
com o mesmo estado, aparecendo ambos os alunos na lista de inscritos, em ambas as réplicas.

Considere o mesmo exemplo, mas agora com 4 alunos:

    - Dois alunos contactam o servidor S1
    - Os outros dois contactam o servidor S2

Depois de fazer a propagação diferida, verifica-se que o número total de inscritos excede a capacidade da turma,
portanto dois destes alunos terão de ser colocados na lista de inscrições canceladas.

Nesta fase, uma das réplicas continua a ser considerada primária. As operações de abertura e fecho de inscrições,
continuam a só poder ser realizadas no primário.

2.2 Componentes opcionais da fase 3
------------------- 

Os alunos poderão alterar as estruturas de dados mantidas pelo servidor, assim como a informação trocada entre
servidores, para aplicar as políticas de reconciliação que considerem mais indicadas.

Os alunos poderão também desenvolver mecanismos extra que permitam acrescentar uma terceira réplica já com o sistema em
funcionamento. Reservamos 2 valores adicionais nesta fase para os alunos que conseguirem desenvolver corretamente um
mecanismo deste tipo (por outras palavras, os alunos podem ter uma nota superior a 20 valores na fase 3, o que poderá
compensar uma nota mais baixa noutra fase).

3 Interfaces do serviço
------------------------

O servidor (ou servidores, conforme a fase) mantém o estado necessário para fornecer o serviço. Este estado inclui:

- Estado da turma (recorda-se que, para simplificação, existe apenas uma turma)

A turma possui o seguinte estado associado

- Capacidade da turma
- Indicação se as inscrições estão abertas ou fechadas
- Lista de alunos inscritos na turma
- Lista de alunos cuja inscrição foi revogada (isto pode acontecer na fase 3, devido ao modelo de coerência eventual)

Cada servidor exporta múltiplas interfaces. Cada interface está pensada para expor dados para entidades distintas. Como
foi referido, no projecto considera-se que existem três tipos distintos de clientes, nomeadamente os docentes, os alunos
e os administradores. Para além disso, os servidores exportam uma quarta interface pensada para ser invocada por outros
servidores (no caso em que os servidores estão replicados, e necessitam de comunicar entre si).

3.1 Interface do docente
-------------------


O docente pode invocar as seguintes funções:

- `openEnrollments` -- recebe a lotação da turma e abre as inscrições nesta
- `closeEnrollments` -- fecha as inscrições na turma
- `list` -- lista o estado das inscrições, apresentando a lista de alunos inscritos, a lista de inscrições canceladas e
  a capacidade total da turma
- `cancelEnrollment` -- recebe o identificador do *aluno*, removendo a inscrição de um dado *aluno* e colocando-o na
  lista de inscrições canceladas da turma

3.2 Interface do aluno
-------------------

O aluno pode invocar as seguintes funções

- `list` -- lista o estado das inscrições, apresentando a lista de alunos inscritos, a lista de inscrições canceladas e
  a capacidade total da turma
- `enroll` -- recebe o nome e o identificador do *aluno*, inscrevendo o *aluno*

3.3 Interface do administrador
-------------------

- `activate` -- coloca o servidor em modo **ATIVO** (este é o comportamento por omissão), em que responde a todos os
  pedidos
- `deactivate` -- coloca o servidor em modo **INATIVO**. Neste modo o servidor responde com o erro "DISABLED" a todos os
  pedidos dos docentes e dos alunos
- `dump` -- reporta o estado do servidor
- `deactivateGossip` -- termina o processo de propagação diferida entre réplicas (só para a fase 3)
- `activateGossip` -- inicia o processo de propagação diferida entre réplicas (só para a fase 3)
- `gossip` -- força uma réplica a fazer uma propagação diferida para a(s) outra(s) réplica(s) (só para a fase 3)

Todos os comandos do administrador **podem** receber como argumento `P|S` indicando se o servidor alvo dessa operação é o primário ou o secundário, se tal argumento não for indicado, a operação deve ser realizada no primário.

3.4 Interface entre servidores
-------------------

-`propagateState` -- um servidor envia o seu estado a outra réplica.

4 Servidor de nomes
------------------------

O servidor de nomes permite aos servidores registarem o seu endereço para ser conhecido por outros que estejam presentes
no sistema.

Um servidor, quando se regista, indica o nome do serviço (neste caso *turmas*), o seu endereço e um qualificador, que
pode assumir os valores 'P' (primário) ou 'S' (secundários).

Este servidor está à escuta no porto **5000** e assume-se que nunca falha.

Os clientes (processos docente, aluno, ou administrador) podem obter o endereço dos servidores, fornecendo o nome do
serviço e o qualificador.

Na fase 3, cada um dos servidores também pode usar o servidor de nomes para ficar a saber o endereço do outro servidor.

5 Processos
------------------------


O sistema será instalado recorrendo a 8 processos no máximo.

Todos os processos cliente deverão mostrar o símbolo *>* sempre que se encontrarem à espera que um comando seja
introduzido.

No caso dos comandos `list` e `dump` se a operação for bem sucedida, deve imprimir o estado da turma (exemplo a seguir),
caso a operação falhe deve imprimir a mensagem associada ao código de resposta recebido. Os restantes comandos devem
sempre imprimir a mensagem associada ao código de resposta recebido.

```
> list
ClassState{
	capacity=5,
	openEnrollments=true,
	enrolled=[
		Student{
			Id='aluno0012',
			Name='Joaquim Freire'
		}],
	discarded=[]
}

> exit
```

O formato de impressão do estado da turma e as mensagens associadas a cada código de resposta encontram-se definidas na
classe `Stringify` que se encontra no módulo `Utilities` disponibilizado no canal *#anuncios-projeto* do discord.

Para cada interface exportada pelos servidores (incluindo o servidor de nomes), será gerada uma biblioteca que deve ser
usada pelos processos que invoquem chamadas a procedimentos remotos nessa interface. Por exemplo, o processo *docente*
deve usar a biblioteca cliente do servidor de nomes (nas fases 2 e 3) e a biblioteca cliente da interface *docente* do
servidor de *turmas*.

Todos os processos devem poder ser lançados com uma opção "-debug". Se esta opção for seleccionada, o processo deve
imprimir para o "stderr" mensagens que descrevam as acções que executa. O formato destas mensagens é livre mas deve
ajudar a depurar o código. Deve também ser pensado para ajudar a perceber o fluxo das execuções durante a discussão
final.


5.1 Servidores primário/secundário
--------------

O servidor (ou servidores nas fases 2 e 3) devem ser lançados recebendo como argumentos o endereço, o porto e uma flag
que identifica se um servidor é ou não o primário.

Na fase 1, o servidor será sempre primário.

Por exemplo, um servidor primário pode ser lançado da seguinte forma a partir da pasta `ClassServer` (**$** representa a *shell* do sistema operativo):

`$ mvn exec:java -Dexec.args="localhost 2001 P"`

Um servidor secundário pode ser lançado da seguinte forma:

`$ mvn exec:java -Dexec.args="localhost 2002 S"`

5.2 Servidor de nomes
-------------

O servidor de nomes deve ser lançado sem argumentos e ficará à escuta no porto `5000`, podendo ser lançado a partir da pasta `NamingServer` da seguinte forma:

`$ mvn exec:java`

5.3 Cliente *docente*
-------------

O cliente docente deve ser lançado sem parâmetros, podendo ser lançado a partir da pasta `Professor` com o seguinte comando:

`$ mvn exec:java`

Exemplo de uma interação com o cliente docente:

```
> openEnrollments 50
The action completed successfully.

> closeEnrollments 
The action completed successfully.

> list  
ClassState{
	capacity=50,
	openEnrollments=false,
	enrolled=[
		Student{
			Id='aluno0012',
			Name='Joaquim Freire'
		}],
	discarded=[]
}

> cancelEnrollment aluno0012
The action completed successfully.

> list
ClassState{
	capacity=50,
	openEnrollments=false,
	enrolled=[],
	discarded=[
		Student{
			Id='aluno0012',
			Name='Joaquim Freire'
		}]
}

> exit
```

5.4 Cliente *aluno*
-----------

O cliente aluno deve ser lançado recebendo como argumento o identificador do aluno e o seu nome, a partir da pasta `Student` como por exemplo:

`$ mvn exec:java -Dexec.args="aluno0012 Joaquim Freire"`

O identificador do aluno deverá ser do tipo `alunoXXXX` em que `XXXX` representa um número de 4 dígitos, ou seja,
constituído pela string `aluno` e por 4 dígitos.

O nome do aluno deverá ter entre 3 e 30 caracteres.

```
> list  
ClassState{
	capacity=0,
	openEnrollments=false,
	enrolled=[],
	discarded=[]
}

> exit
```

5.5 Cliente *administrador*
---------


O cliente administrador não necessita de qualquer tipo de argumentos, sendo apenas lançado da seguinte forma na pasta `Admin`:

`$ mvn exec:java`

```
> deactivate P
The action completed successfully.

> dump 
ClassState{
	capacity=0,
	openEnrollments=false,
	enrolled=[],
	discarded=[]
}

> activate P
The action completed successfully.

> deactivate S
The action completed successfully.

> exit
```

6 Tecnologia
------------

Todos os componentes do projeto têm de ser implementados na linguagem de
programação [Java](https://docs.oracle.com/javase/specs/).

A ferramenta de construção a usar, obrigatoriamente, é o [Maven](https://maven.apache.org/).

### Invocações remotas

A invocação remota de serviços deve ser suportada por serviços [gRPC](https://grpc.io/).

Os serviços devem ser descritos no formato [*Protocol Buffers*](https://developers.google.com/protocol-buffers),
abreviado por `protobuf`.

Cabe ao grupo definir os *protocol buffers* que julguem necessários para concretizar o projeto.

Não existem contratos de serviço pré-definidos.

Os contratos devem tirar partido do sistema de tipos disponível.

Os objetos Java a criar, `TurmasFrontend`, `NamingFrontend`, etc., devem encapsular o *stub* gerado pela biblioteca
gRPC.

Deve expor métodos para cada operação com os mesmos tipos de dados gerados a partir dos *protocol buffers*.

### Persistência

Não se exige nem será valorizado o armazenamento persistente do estado dos servidores.

### Validações

Os argumentos das operações devem ser validados obrigatóriamente e de forma estrita pelo servidor.

Os clientes podem optar por também validar, de modo a evitar pedidos desnecessários para o servidor, mas podem optar por
uma versão mais simples da validação.

### Faltas

Se durante a execução surgirem faltas, ou seja, acontecimentos inesperados, o programa deve apanhar a exceção, imprimir
informação sucinta e pode parar de executar.

Se for um servidor, o programa deve responder ao cliente com um código de erro adequado.

Se for um dos clientes, pode decidir parar com o erro recebido ou fazer novas tentativas de pedido.


7 Resumo
------------

Em resumo, é necessário implementar:

o servidor, *turmas*;

o cliente docente, *docente*;

o cliente aluno, *aluno*, e

o cliente administrador, *turmas_admin*.

Todos os clientes oferecem uma interface-utilizador baseada na linha de comandos.


8 Avaliação
------------

8.1 Fotos
---------

Cada membro da equipa tem que atualizar o Fénix com uma foto, com qualidade, tirada nos últimos 2 anos, para facilitar a
identificação e comunicação.

8.2 Identificador de grupo
--------------------------

O identificador do grupo tem o formato `GXX`, onde `G` representa o campus e `XX` representa o número do grupo de SD
atribuído pelo Fénix. Por exemplo, o grupo A22 corresponde ao grupo 22 sediado no campus Alameda; já o grupo T07
corresponde ao grupo 7 sediado no Taguspark.

O grupo deve identificar-se no documento `README.md` na pasta raíz do projeto.

Em todos os ficheiros de configuração `pom.xml` e de código-fonte, devem substituir `GXX` pelo identificador de grupo.

Esta alteração é importante para a gestão de dependências, para garantir que os programas de cada grupo utilizam sempre
os módulos desenvolvidos pelo próprio grupo.

8.3 Colaboração
---------------

O [Git](https://git-scm.com/doc) é um sistema de controlo de versões do código fonte que é uma grande ajuda para o
trabalho em equipa.

Toda a partilha de código para trabalho deve ser feita através do [GitHub](https://github.com).

O repositório de cada grupo está disponível em: https://github.com/tecnico-distsys/GXX-Turmas/ (substituir `GXX` pelo
identificador de grupo).

A atualização do repositório deve ser feita com regularidade, correspondendo à distribuição de trabalho entre os membros
da equipa e às várias etapas de desenvolvimento.

Cada elemento do grupo deve atualizar o repositório do seu grupo à medida que vai concluindo as várias tarefas que lhe
foram atribuídas.

8.4 Entregas
------------

As entregas do projeto serão feitas também através do repositório GitHub.

A cada parte do projeto a entregar estará associada uma [*tag*](https://git-scm.com/book/en/v2/Git-Basics-Tagging).

Cada grupo tem que marcar o código que representa cada entrega a realizar com uma *tag* específica -- `SD_P1` -- antes
da hora limite de entrega.

8.5 Valorização
---------------


As datas limites de entrega estão definidas no site dos laboratórios: (https://tecnico-distsys.github.io)

### Qualidade do código

A avaliação da qualidade engloba os seguintes aspetos:

- Configuração correta (POMs);

- Código legível (incluindo comentários relevantes);

- [Tratamento de exceções adequado](http://disciplinas.tecnico.ulisboa.pt/leic-sod/2019-2020/labs/03-tools-sockets/exceptions/index.html)
  ;

- [Sincronização correta](http://disciplinas.tecnico.ulisboa.pt/leic-sod/2019-2020/labs/03-tools-sockets/java-synch/index.html)
  .

8.6 Instalação e demonstração
-----------------------------

As instruções de instalação e configuração de todo o sistema, de modo a que este possa ser colocado em funcionamento,
devem ser colocadas no documento `README.md`.

Este documento tem de estar localizado na raiz do projeto e tem que ser escrito em formato [*
MarkDown*](https://guides.github.com/features/mastering-markdown/).

Cada grupo deve preparar também um mini relatório, que não deve exceder as **500** palavras, explicando a sua solução para a fase 3,
e que não deve repetir informação que já esteja no enunciado. Este documento deve estar na raiz do projeto e deve ter o nome `REPORT.md`

8.7 Discussão
-------------

As notas das várias partes são indicativas e sujeitas a confirmação na discussão final, na qual todo o trabalho
desenvolvido durante o semestre será tido em conta.

As notas a atribuir serão individuais, por isso é importante que a divisão de tarefas ao longo do trabalho seja
equilibrada pelos membros do grupo.

Todas as discussões e revisões de nota do trabalho devem contar com a participação obrigatória de todos os membros do
grupo.

8.8 Atualizações
----------------

Para acompanhar as novidades sobre o projeto, consultar regularmente
a [página Web dos laboratórios](https://tecnico-distsys.github.io).

Caso venham a surgir correções ou clarificações neste documento, podem ser consultadas no histórico (_History_).

**Bom trabalho!**
 