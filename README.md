<h1 align="center">Distributed Systems Project</h1>

This project was done by a group of 3 in 2022 with an educational purpose for the Distributed Systems lecture in Instituto Superior Técnico. The work was divided into 3 deliveries to which we ended with an overall **A grade** (17.04 out of 20) for all deliveries together.

### Team Members:


| Name          | User                               | Email                                       |
|---------------|------------------------------------|---------------------------------------------|
| Filipe Neves  | <https://github.com/Manittas>      | <mailto:filipe.cabral.n@tecnico.ulisboa.pt> |
| Diogo Braz    | <https://github.com/difb70>        | <mailto:diogo.braz@tecnico.ulisboa.pt>      |
| Tiago Martins | <https://github.com/tiagopmartins> | <mailto:tiagopmartins@tecnico.ulisboa.pt>   |

--------------------------------


<h2 align="center">Getting Started</h2>

The overall system is made up of several modules. The main server is the _ClassServer_. The clients are the _Student_,
the _Professor_ and the _Admin_. The definition of messages and services is in the _Contract_. the naming server 
is the _NamingServer_. The I/O format and output messages are in _Utilities_.

See the [Project Statement](https://github.com/tecnico-distsys/Turmas) or a complete domain and system description.

### Prerequisites

The Project is configured with Java 17 (which is only compatible with Maven >= 3.8), but if you want to use Java 11 you
can too, just downgrade the version in the POMs.

To confirm that you have them installed and which versions they are, run in the terminal:

```s
javac -version
mvn -version
```

### Installation And Configuration For Testing

In the POM file in _ClassServer_ the flag argument "P" can be switched between "P" or "S" to specify if the server is primary or secondary respectively. When opening the secondary server, you need to change the port in the POM file.

The debug argument can be removed from the command arguments int the POM files of _ClassServer_ or _NamingServer_ if the intention is to run the servers with the debug mode deactivated.

To compile and install all modules, run the following command in the root of the project to install it:

```s
/A42-Turmas$    mvn clean install
```

Open 6 terminals, proceed to each folder and insert the commands in their respective folder:

```s
/A42-Turmas/NamingServer$   mvn exec:java
/A42-Turmas/ClassServer$    mvn exec:java -Dexec.args="<<host>> <<port for server P>> P <<debug flag if wanted>>"
/A42-Turmas/ClassServer$    mvn exec:java -Dexec.args="<<host>> <<port for server S>> S <<debug flag if wanted>>"
/A42-Turmas/Admin$          mvn exec:java
/A42-Turmas/Professor$      mvn exec:java    
/A42-Turmas/Student$        mvn exec:java -Dexec.args="<<studentId>> <<studentName>>"
```

Note: if the user is in a macOS needs to use the following script to run the tests:

```s
run_mac.bash
```

## Built With

* [Maven](https://maven.apache.org/) - Build and dependency management tool;
* [gRPC](https://grpc.io/) - RPC framework.
