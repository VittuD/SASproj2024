-- MySQL dump 10.13  Distrib 5.7.26, for osx10.10 (x86_64)
--
-- Host: 127.0.0.1    Database: catering
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
--
-- Table structure for table `Events`
--

DROP TABLE IF EXISTS `Events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Events` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `name` varchar(128) DEFAULT NULL,
                          `date_start` date DEFAULT NULL,
                          `date_end` date DEFAULT NULL,
                          `expected_participants` int(11) DEFAULT NULL,
                          `organizer_id` int(11) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Events`
--

LOCK TABLES `Events` WRITE;
/*!40000 ALTER TABLE `Events` DISABLE KEYS */;
INSERT INTO `Events` VALUES (1,'Convegno Agile Community','2020-09-25','2020-09-25',100,2),(2,'Compleanno di Manuela','2020-08-13','2020-08-13',25,2),(3,'Fiera del Sedano Rapa','2020-10-02','2020-10-04',400,1);
/*!40000 ALTER TABLE `Events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MenuFeatures`
--

DROP TABLE IF EXISTS `MenuFeatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MenuFeatures` (
                                `menu_id` int(11) NOT NULL,
                                `name` varchar(128) NOT NULL DEFAULT '',
                                `value` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MenuFeatures`
--

LOCK TABLES `MenuFeatures` WRITE;
/*!40000 ALTER TABLE `MenuFeatures` DISABLE KEYS */;
INSERT INTO `MenuFeatures` VALUES (80,'Richiede cuoco',0),(80,'Buffet',0),(80,'Richiede cucina',0),(80,'Finger food',0),(80,'Piatti caldi',0),(82,'Richiede cuoco',0),(82,'Buffet',0),(82,'Richiede cucina',0),(82,'Finger food',0),(82,'Piatti caldi',0),(86,'Richiede cuoco',0),(86,'Buffet',0),(86,'Richiede cucina',0),(86,'Finger food',0),(86,'Piatti caldi',0);
/*!40000 ALTER TABLE `MenuFeatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MenuItems`
--

DROP TABLE IF EXISTS `MenuItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MenuItems` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `menu_id` int(11) NOT NULL,
                             `section_id` int(11) DEFAULT NULL,
                             `description` tinytext,
                             `recipe_id` int(11) NOT NULL,
                             `position` int(11) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MenuItems`
--

LOCK TABLES `MenuItems` WRITE;
/*!40000 ALTER TABLE `MenuItems` DISABLE KEYS */;
INSERT INTO `MenuItems` VALUES (96,80,0,'Croissant vuoti',9,0),(97,80,0,'Croissant alla marmellata',9,1),(98,80,0,'Pane al cioccolato mignon',10,2),(99,80,0,'Panini al latte con prosciutto crudo',12,4),(100,80,0,'Panini al latte con prosciutto cotto',12,5),(101,80,0,'Panini al latte con formaggio spalmabile alle erbe',12,6),(102,80,0,'Girelle all\uvetta mignon',11,3),(103,82,0,'Biscotti',13,1),(104,82,0,'Lingue di gatto',14,2),(105,82,0,'Bigné alla crema',15,3),(106,82,0,'Bigné al caffè',15,4),(107,82,0,'Pizzette',16,5),(108,82,0,'Croissant al prosciutto crudo mignon',9,6),(109,82,0,'Tramezzini tonno e carciofini mignon',17,7),(112,86,41,'Vitello tonnato',1,0),(113,86,41,'Carpaccio di spada',2,1),(114,86,41,'Alici marinate',3,2),(115,86,42,'Penne alla messinese',5,0),(116,86,42,'Risotto alla zucca',20,1),(117,86,43,'Salmone al forno',8,0),(118,86,44,'Sorbetto al limone',18,0),(119,86,44,'Torta Saint Honoré',19,1);
/*!40000 ALTER TABLE `MenuItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MenuSections`
--

DROP TABLE IF EXISTS `MenuSections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MenuSections` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `menu_id` int(11) NOT NULL,
                                `name` tinytext,
                                `position` int(11) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MenuSections`
--

LOCK TABLES `MenuSections` WRITE;
/*!40000 ALTER TABLE `MenuSections` DISABLE KEYS */;
INSERT INTO `MenuSections` VALUES (41,86,'Antipasti',0),(42,86,'Primi',1),(43,86,'Secondi',2),(44,86,'Dessert',3),(45,87,'Antipasti',0);
/*!40000 ALTER TABLE `MenuSections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menus`
--

DROP TABLE IF EXISTS `Menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Menus` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `title` tinytext,
                         `owner_id` int(11) DEFAULT NULL,
                         `published` tinyint(1) DEFAULT '0',
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`owner_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menus`
--

LOCK TABLES `Menus` WRITE;
/*!40000 ALTER TABLE `Menus` DISABLE KEYS */;
INSERT INTO `Menus` VALUES (80,'Coffee break mattutino',2,1),(82,'Coffee break pomeridiano',2,1),(86,'Cena di compleanno pesce',3,1);
/*!40000 ALTER TABLE `Menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Assignment`
--
DROP TABLE IF EXISTS `Assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Assignment (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            description VARCHAR(255),
                            estimatedTime TIME,  -- Assuming 'Duration' can be stored as TIME or INTERVAL
                            completed BOOLEAN,
                            cooks JSON,          -- Assuming 'List of Cook' can be stored as JSON
                            quantity INT,
                            kT INT               -- Assuming KitchenTurn is another table; this would be a foreign key
);
ALTER TABLE Assignment
    ADD CONSTRAINT fk_kitchenTurn
        FOREIGN KEY (kT) REFERENCES KitchenTurn(id);

--
-- Dumping data for table `Assignment`
--

-- Assuming 'KitchenTurn' table exists, add a foreign key relationship:



--
-- Table structure for table `Recipes`
--

DROP TABLE IF EXISTS `Recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recipes` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` tinytext NOT NULL,
                           `description` tinytext,
                           `instructions` tinytext,
                           `preparations_id` Json,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recipes`
--
LOCK TABLES `Recipes` WRITE;
/*!40000 ALTER TABLE `Recipes` DISABLE KEYS */;
INSERT INTO `Recipes` (`id`, `name`, `description`, `instructions`, `preparations_id`) VALUES
                                                                                           (1,'Vitello tonnato', 'Vitello con salsa tonnata', 'fare la salsa tonnata e metterla sul vitello', '["1,15"]'),
                                                                                           (2,'Carpaccio di spada', 'description2', 'instructions2', '["2"]'),
                                                                                           (3,'Alici marinate', 'description3', 'instructions3', '["3"]'),
                                                                                           (4,'Insalata di riso', 'description4', 'instructions4', '["4"]'),
                                                                                           (5,'Penne al sugo di baccalà', 'description5', 'instructions5', '["5"]'),
                                                                                           (6,'Pappa al pomodoro', 'description6', 'instructions6', '["6"]'),
                                                                                           (7,'Hamburger con bacon e cipolla caramellata', 'description7', 'instructions7', '["7"]'),
                                                                                           (8,'Salmone al forno', 'description8', 'instructions8', '["8"]'),
                                                                                           (9,'Croissant', 'description9', 'instructions9', '["9"]'),
                                                                                           (10,'Pane al cioccolato', 'description10', 'instructions10', '["10"]'),
                                                                                           (11,'Girelle all\uvetta', 'description11', 'instructions11', '["11"]'),
                                                                                           (12,'Panini al latte', 'description12', 'instructions12', '["12"]'),
                                                                                           (13,'Biscotti di pasta frolla', 'description13', 'instructions13', '["13"]'),
                                                                                           (14,'Lingue di gatto', 'description14', 'instructions14', '["14"]');
/*!40000 ALTER TABLE `Recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Preparation`
--
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
DROP TABLE IF EXISTS `Preparation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Preparation (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             `name` tinytext NOT NULL,
                             `description` tinytext,
                             `instructions` tinytext
)ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `Preparation`
--
LOCK TABLES `Preparation` WRITE;
/*!40000 ALTER TABLE `Preparation` DISABLE KEYS */;
INSERT INTO `Preparation` values (1,'Salsa Tonnata','Salsa a base di tonno e maionese','Mescolare il tonno con la maionese'),(2,'Carpaccio di spada','Fettine di spada con olio e limone','Tagliare la spada a fettine sottili e condire con olio e limone'),(3,'Alici marinate','Alici con olio e limone','Mettere le alici in una ciotola con olio e limone'),(4,'Insalata di riso','Riso con verdure','Cuocere il riso e condire con verdure'),(5,'Penne al sugo di baccalà','Pasta con sugo di baccalà','Cuocere la pasta e condire con sugo di baccalà'),(6,'Pappa al pomodoro','Pappa con pomodoro','Cuocere la pappa e condire con pomodoro'),(7,'Hamburger con bacon e cipolla caramellata','Hamburger con bacon e cipolla','Cuocere l\'hamburger e condire con bacon e cipolla'),(8,'Salmone al forno','Salmone con verdure','Cuocere il salmone e condire con verdure'),(9,'Croissant','Pasta sfoglia con burro','Cuocere la pasta sfoglia con burro'),(10,'Pane al cioccolato','Pane con cioccolato','Cuocere il pane e condire con cioccolato'),(11,'Girelle all\'uvetta','Girelle con uvetta','Cuocere le girelle e condire con uvetta'),(12,'Panini al latte','Panini con latte','Cuocere i panini e condire con latte'),(13,'Biscotti di pasta frolla','Biscotti con pasta frolla','Cuocere i biscotti e condire con pasta frolla'),(14,'Lingue di gatto','Biscotti con zucchero','Cuocere i biscotti e condire con zucchero'),(15,'Filetto di vitello bollito','filetto di vitello bollito','mettere in una pentola il filetto insieme a verdure aromatiche e acqua');
/*!40000 ALTER TABLE `Preparation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
                         `id` char(1) NOT NULL,
                         `role` varchar(128) NOT NULL DEFAULT 'servizio',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES ('c','cuoco'),('h','chef'),('o','organizzatore'),('s','servizio');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Services`
--

DROP TABLE IF EXISTS `Services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Services` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `event_id` int(11) NOT NULL,
                            `name` varchar(128) DEFAULT NULL,
                            `place` varchar(128) DEFAULT NULL,
                            `approved_menu_id` int(11) DEFAULT '0',
                            `service_date` date DEFAULT NULL,
                            `time_start` time DEFAULT NULL,
                            `time_end` time DEFAULT NULL,
                            `expected_participants` int(11) DEFAULT NULL,
                            `service_summary` Json DEFAULT NULL,
                            `kitchen_turns_id` Json DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            FOREIGN KEY (`event_id`) REFERENCES `Events` (`id`),
                            FOREIGN KEY (`approved_menu_id`) REFERENCES `Menus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Services`
--

LOCK TABLES `Services` WRITE;
/*!40000 ALTER TABLE `Services` DISABLE KEYS */;
INSERT INTO `Services` VALUES (1,2,'Cena','CannavacciuoloBistrot',86,'2020-08-13','20:00:00','23:30:00',25,NULL,NULL);
/*!40000 ALTER TABLE `Services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Turns`
--

DROP TABLE IF EXISTS `Turns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Turns` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `service_id` int(11) NOT NULL,
                         `date` date DEFAULT NULL,
                         `time_start` time DEFAULT NULL,
                         `time_end` time DEFAULT NULL,
                         `published` boolean DEFAULT '0',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Table structure for table `KitchenTurns`
--

DROP TABLE IF EXISTS `KitchenTurn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE KitchenTurn (
                             `id` INT PRIMARY KEY,
                             `full` boolean DEFAULT '0',
                             `kitchen_id` INT,
                             FOREIGN KEY (id) REFERENCES Turns(id),
                             FOREIGN KEY (kitchen_id) REFERENCES Kitchen(id)
);

--
-- Table structure for table `Kitchen`
--

DROP TABLE IF EXISTS `Kitchen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Kitchen (
                         `id` INT,
                         `name` varchar(128) DEFAULT NULL,
                         PRIMARY KEY (`id`)
);

--
-- Table structure for table `UserRoles`
--

DROP TABLE IF EXISTS `UserRoles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRoles` (
                             `user_id` int(11) NOT NULL,
                             `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRoles`
--

LOCK TABLES `UserRoles` WRITE;
/*!40000 ALTER TABLE `UserRoles` DISABLE KEYS */;
INSERT INTO `UserRoles` VALUES (1,'o'),(2,'o'),(2,'h'),(3,'h'),(4,'h'),(4,'c'),(5,'c'),(6,'c'),(7,'c'),(8,'s'),(9,'s'),(10,'s'),(7,'s');
/*!40000 ALTER TABLE `UserRoles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(128) NOT NULL DEFAULT '',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Carlin'),(2,'Lidia'),(3,'Tony'),(4,'Marinella'),(5,'Guido'),(6,'Antonietta'),(7,'Paola'),(8,'Silvia'),(9,'Marco'),(10,'Piergiorgio');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-05 15:04:25