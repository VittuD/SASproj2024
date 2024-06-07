module catering {
    requires java.sql;
    requires com.google.gson;
    opens catering to com.google.gson;
    requires org.junit.jupiter.api;

    exports catering;

    opens catering.businesslogic.turn to com.google.gson;
    opens catering.businesslogic.assignment to com.google.gson;
    opens catering.businesslogic.user to com.google.gson;
    opens catering.businesslogic.recipe to com.google.gson;
    opens catering.businesslogic.menu to com.google.gson;
    opens catering.businesslogic.event to com.google.gson;
}
    exports catering.businesslogic.assignment;
    exports catering.businesslogic.menu;
    exports catering.businesslogic.event;
    exports catering.businesslogic.recipe;
    exports catering.businesslogic.turn;
    exports catering.businesslogic.user;

    // Open packages for reflection-based frameworks
    opens catering.businesslogic.assignment to org.junit.jupiter.api;
    opens catering.businesslogic.event to org.junit.jupiter.api;
    opens catering.businesslogic.menu to org.junit.jupiter.api;
    opens catering.businesslogic.recipe to org.junit.jupiter.api;
    opens catering.businesslogic.turn to org.junit.jupiter.api;
    opens catering.businesslogic.user to org.junit.jupiter.api;
    opens catering.persistence to org.junit.jupiter.api;
}