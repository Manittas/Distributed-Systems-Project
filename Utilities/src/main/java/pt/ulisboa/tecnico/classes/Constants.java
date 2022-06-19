package pt.ulisboa.tecnico.classes;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    // Constants (general)
    public static final String TURMAS_SERVICE = "Turmas";
    public static final int MAX_TRIES = 5;
    public static final long GOSSIP_TIMEOUT = 1000000000;   // Timeout for the update logs reception (ns)

    // Debug constants
    public static final String DEBUG_FLAG = "-debug";

    public static final String SERVER_STARTED = "Server started.\n";

    public static final String REQUEST_SERVER = "Request received (server): ";
    public static final String RESPONSE_SERVER = "Response sent (server): ";

    public static final String REQUEST_ADMIN = "Request received (admin): ";
    public static final String RESPONSE_ADMIN = "Response sent (admin): ";

    public static final String REQUEST_PROFESSOR = "Request received (professor): ";
    public static final String RESPONSE_PROFESSOR = "Response sent (professor): ";

    public static final String REQUEST_STUDENT = "Request received (student): ";
    public static final String RESPONSE_STUDENT = "Response sent (student): ";

    public static final String SERVER_ACTIVE = "Server is active.\n";
    public static final String SERVER_INACTIVE = "Server is inactive.\n";
    public static final String SERVER_DUMP = "Server dump.\n";
    public static final String SERVER_OPEN_ENROLLMENT = "Opening enrollments with: ";
    public static final String SERVER_CLOSE_ENROLLMENT = "Closing enrollments.\n";
    public static final String SERVER_ENROLL = "Enrolling a student: ";
    public static final String SERVER_CANCEL_ENROLLMENT = "Canceling an enrollment: ";
    public static final String SERVER_LIST = "Listing class information.\n";
    public static final String SERVER_REGISTER = "Registering the server:\n";
    public static final String SERVER_LOOKUP = "Looking for server of type:";
    public static final String SERVER_DELETE = "Deleting server: \n";
    public static final String SERVER_GET_INFO = "Getting info.\n";

    public static final String PROPAGATING_STATE = "Propagating state.\n";
    public static final String PROPAGATING_TO = "Gossiping the update log to -> ";
    public static final String PROPAGATION_FINISHED = "State propagation finished.\n";

    public static final String RECEIVING_STATE = "Receiving state.\n";
    public static final String UPDATING_STATE = "Updating state.\n";
    public static final String UPDATING_STATE_FINISHED = "Finished updating state.\n";

    public static final String SERVER_REQUESTED = "Server requested to the name server\n";
    public static final String SERVERS_REQUESTED = "Servers requested to the name server: ";

    // Commands available
    public static final String OPEN_ENROLLMENTS_CMD = "openEnrollments";
    public static final String CLOSE_ENROLLMENTS_CMD = "closeEnrollments";
    public static final String LIST_CMD = "list";
    public static final String ENROLL_CMD = "enroll";
    public static final String CANCEL_ENROLLMENT_CMD = "cancelEnrollment";
    public static final String EXIT_CMD = "exit";
    public static final String ACTIVATE_CMD = "activate";
    public static final String DEACTIVATE_CMD = "deactivate";
    public static final String DUMP_CMD = "dump";
    public static final String DEACTIVATE_GOSSIP_CMD = "deactivateGossip";
    public static final String ACTIVATE_GOSSIP_CMD = "activateGossip";
    public static final String GOSSIP_CMD = "gossip";
    public static final String REGISTER_CMD = "register";
    public static final String LOOKUP_CMD = "lookup";
    public static final String DELETE_CMD = "delete";
    public static final String GET_INFO_CMD = "getInfo";

    // Server types available
    public static final String PRIMARY_SERVER = "P";
    public static final String SECONDARY_SERVER = "S";
    public static final List<String> SERVER_TYPES = Arrays.asList(PRIMARY_SERVER, SECONDARY_SERVER);

    // Error messages (general)
    public static final String SERVER_UNAVAILABLE = "The server is currently unavailable.\n";
    public static final String INVALID_CMD = "Invalid command.\n";
    public static final String INVALID_ARG = "Invalid argument.\n";
    public static final String INVALID_ARG_NUMBER = "Invalid number of arguments.\n";
    public static final String INVALID_PORT = "Invalid port specified.\n";
    public static final String INVALID_STUDENT_ID = "Please, insert a valid student Id.\n";
    public static final String INVALID_STUDENT_NAME_LENGTH = "Please, insert a valid name with a length between 3 and 30.\n";
    public static final String INVALID_STUDENT = "Please, insert a valid student Id and name.\n";
    public static final String INVALID_CAPACITY = "Please, insert a valid capacity.\n";
    public static final String INVALID_SERVER_TYPE = "Invalid server type. Only P (primary) and S (secondary) servers exist.\n";
    public static final String GOSSIP_IS_OFF = "The gossip if off.\n";
    public static final String NO_OTHER_SERVERS_AVAILABLE = "No other servers are up.\n";

    // Error messages (commands)
    public static final String INVALID_OPEN_ENROLLMENTS_CMD = "Please, insert the command openEnrollments in the correct format.\n";
    public static final String INVALID_CLOSE_ENROLLMENTS_CMD = "Please, insert the command closeEnrollments in the correct format.\n";
    public static final String INVALID_LIST_CMD = "Please, insert the command list in the correct format.\n";
    public static final String INVALID_CANCEL_ENROLLMENT_CMD = "Please, insert the command cancelEnrollment in the correct format.\n";
    public static final String INVALID_EXIT_CMD = "Please, insert the command exit in the correct format.\n";
    public static final String INVALID_ENROLL_CMD = "Please, insert the command enroll in the correct format.\n";
}
