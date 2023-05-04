-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jki
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `accountID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`accountID`),
  UNIQUE KEY `accountID_UNIQUE` (`accountID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1357 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1352,'Alice','11111111',0),(1353,'Bob','22222222',0),(1354,'Carol','33333333',1),(1355,'Dan','44444444',0);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts_seq`
--

DROP TABLE IF EXISTS `accounts_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts_seq`
--

LOCK TABLES `accounts_seq` WRITE;
/*!40000 ALTER TABLE `accounts_seq` DISABLE KEYS */;
INSERT INTO `accounts_seq` VALUES (1451);
/*!40000 ALTER TABLE `accounts_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerts` (
  `alertID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `inventoryID` int NOT NULL,
  `itemID` int NOT NULL,
  `triggers` varchar(255) NOT NULL,
  `threshold` varchar(255) NOT NULL,
  PRIMARY KEY (`alertID`),
  UNIQUE KEY `alertID_UNIQUE` (`alertID`),
  KEY `accountID_idx` (`accountID`),
  KEY `inventoryID_idx` (`inventoryID`),
  KEY `itemID_idx` (`itemID`),
  CONSTRAINT `accountID_alert` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE,
  CONSTRAINT `inventoryID_alert` FOREIGN KEY (`inventoryID`) REFERENCES `inventories` (`inventoryID`) ON DELETE CASCADE,
  CONSTRAINT `itemID_alert` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=565 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts`
--

LOCK TABLES `alerts` WRITE;
/*!40000 ALTER TABLE `alerts` DISABLE KEYS */;
INSERT INTO `alerts` VALUES (552,1352,202,1102,'quantity','50'),(553,1352,202,1103,'quantity','15'),(557,1352,203,1104,'quantity','2'),(558,1353,204,1105,'date','2023-04-20'),(559,1353,204,1106,'date','2023-04-05'),(560,1353,204,1107,'date','2023-04-01'),(562,1354,205,1108,'quantity','10'),(563,1354,206,1109,'quantity','15'),(564,1354,206,1111,'date','2023-04-10');
/*!40000 ALTER TABLE `alerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alerts_seq`
--

DROP TABLE IF EXISTS `alerts_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerts_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts_seq`
--

LOCK TABLES `alerts_seq` WRITE;
/*!40000 ALTER TABLE `alerts_seq` DISABLE KEYS */;
INSERT INTO `alerts_seq` VALUES (651);
/*!40000 ALTER TABLE `alerts_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desirables`
--

DROP TABLE IF EXISTS `desirables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desirables` (
  `desirableID` int NOT NULL,
  `accountID` int NOT NULL,
  `shoppingListID` int NOT NULL,
  `storeID` int NOT NULL,
  `itemID` int NOT NULL,
  `quantity` int NOT NULL,
  `checked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`desirableID`),
  KEY `accountID_idx` (`accountID`),
  KEY `shoppingListID_idx` (`shoppingListID`),
  KEY `storeID_idx` (`storeID`),
  KEY `itemID_idx` (`itemID`),
  CONSTRAINT `accountID_desirables` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE,
  CONSTRAINT `itemID_desirables` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`) ON DELETE CASCADE,
  CONSTRAINT `shoppingListID_desirables` FOREIGN KEY (`shoppingListID`) REFERENCES `shopping_lists` (`shoppingListID`) ON DELETE CASCADE,
  CONSTRAINT `storeID_desirables` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desirables`
--

LOCK TABLES `desirables` WRITE;
/*!40000 ALTER TABLE `desirables` DISABLE KEYS */;
INSERT INTO `desirables` VALUES (702,1352,202,402,1102,100,0),(703,1352,202,402,1103,10,0),(704,1352,203,403,1104,1,0),(705,1353,204,404,1105,5,0),(706,1353,204,404,1106,20,0),(707,1353,204,404,1107,1,0),(708,1354,205,405,1108,10,0),(709,1354,206,405,1109,15,0),(710,1354,206,406,1111,10,0);
/*!40000 ALTER TABLE `desirables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desirables_seq`
--

DROP TABLE IF EXISTS `desirables_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desirables_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desirables_seq`
--

LOCK TABLES `desirables_seq` WRITE;
/*!40000 ALTER TABLE `desirables_seq` DISABLE KEYS */;
INSERT INTO `desirables_seq` VALUES (801);
/*!40000 ALTER TABLE `desirables_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventories`
--

DROP TABLE IF EXISTS `inventories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventories` (
  `inventoryID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`inventoryID`),
  UNIQUE KEY `inventoryID_UNIQUE` (`inventoryID`),
  KEY `accountID_inventories_idx` (`accountID`),
  CONSTRAINT `accountID_inventories` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventories`
--

LOCK TABLES `inventories` WRITE;
/*!40000 ALTER TABLE `inventories` DISABLE KEYS */;
INSERT INTO `inventories` VALUES (202,1352,'Refrigerator'),(203,1352,'Freezer'),(204,1353,'Minifridge'),(205,1354,'Barrel'),(206,1354,'Box');
/*!40000 ALTER TABLE `inventories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventories_seq`
--

DROP TABLE IF EXISTS `inventories_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventories_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventories_seq`
--

LOCK TABLES `inventories_seq` WRITE;
/*!40000 ALTER TABLE `inventories_seq` DISABLE KEYS */;
INSERT INTO `inventories_seq` VALUES (301);
/*!40000 ALTER TABLE `inventories_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  UNIQUE KEY `itemID_UNIQUE` (`itemID`),
  KEY `accountID_idx` (`accountID`),
  CONSTRAINT `accountID` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1102,1352,'Almond','Nut',''),(1103,1352,'Apple','Fruit','Fuji'),(1104,1352,'Avocado','Fruit',''),(1105,1353,'Banana','Fruit',''),(1106,1353,'Berry','Fruit','Blueberry'),(1107,1353,'Bread','Grain','Whole Wheat'),(1108,1354,'Cabbage','Vegetable',''),(1109,1354,'Carrot','Vegetable',''),(1111,1354,'Cookie','Dessert','Oatmeal');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items_seq`
--

DROP TABLE IF EXISTS `items_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items_seq`
--

LOCK TABLES `items_seq` WRITE;
/*!40000 ALTER TABLE `items_seq` DISABLE KEYS */;
INSERT INTO `items_seq` VALUES (1201);
/*!40000 ALTER TABLE `items_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `priceID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `itemID` int NOT NULL,
  `storeID` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`priceID`),
  UNIQUE KEY `priceID_UNIQUE` (`priceID`),
  KEY `accountID_prices_idx` (`accountID`),
  KEY `itemID_prices_idx` (`itemID`),
  KEY `storeID_prices_idx` (`storeID`),
  CONSTRAINT `accountID_prices` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE,
  CONSTRAINT `itemID_prices` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`) ON DELETE CASCADE,
  CONSTRAINT `storeID_prices` FOREIGN KEY (`storeID`) REFERENCES `stores` (`storeID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=966 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (902,1352,1102,402,5),(952,1352,1103,402,0.5),(953,1352,1104,403,6.25),(954,1353,1105,404,1),(955,1353,1106,404,1),(956,1353,1107,404,2),(957,1354,1108,405,2),(958,1354,1109,406,0.75),(959,1354,1111,406,1.25);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices_seq`
--

DROP TABLE IF EXISTS `prices_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices_seq`
--

LOCK TABLES `prices_seq` WRITE;
/*!40000 ALTER TABLE `prices_seq` DISABLE KEYS */;
INSERT INTO `prices_seq` VALUES (1051);
/*!40000 ALTER TABLE `prices_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shares`
--

DROP TABLE IF EXISTS `shares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shares` (
  `shareID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `subjectID` int NOT NULL,
  `shareeID` int NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`shareID`),
  UNIQUE KEY `shareID_UNIQUE` (`shareID`),
  KEY `accountID_shares_idx` (`accountID`),
  CONSTRAINT `accountID_shares` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE,
  CONSTRAINT `shareeID_shares` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=413 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shares`
--

LOCK TABLES `shares` WRITE;
/*!40000 ALTER TABLE `shares` DISABLE KEYS */;
INSERT INTO `shares` VALUES (402,1352,202,1353,'inventory'),(403,1352,203,1353,'inventory'),(404,1353,204,1352,'inventory'),(405,1354,205,1352,'inventory'),(406,1354,205,1353,'inventory'),(408,1352,202,1353,'shoppingList'),(409,1352,203,1353,'shoppingList'),(410,1353,204,1352,'shoppingList'),(411,1354,205,1352,'shoppingList'),(412,1354,205,1353,'shoppingList');
/*!40000 ALTER TABLE `shares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shares_seq`
--

DROP TABLE IF EXISTS `shares_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shares_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shares_seq`
--

LOCK TABLES `shares_seq` WRITE;
/*!40000 ALTER TABLE `shares_seq` DISABLE KEYS */;
INSERT INTO `shares_seq` VALUES (501);
/*!40000 ALTER TABLE `shares_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_lists`
--

DROP TABLE IF EXISTS `shopping_lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_lists` (
  `shoppingListID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`shoppingListID`),
  UNIQUE KEY `shoppingListID_UNIQUE` (`shoppingListID`),
  KEY `accountID_shoppingLists_idx` (`accountID`),
  CONSTRAINT `accountID_shoppingLists` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_lists`
--

LOCK TABLES `shopping_lists` WRITE;
/*!40000 ALTER TABLE `shopping_lists` DISABLE KEYS */;
INSERT INTO `shopping_lists` VALUES (202,1352,'Today'),(203,1352,'Tomorrow'),(204,1353,'4-12-2023'),(205,1354,'Christmas'),(206,1354,'New Years');
/*!40000 ALTER TABLE `shopping_lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_lists_seq`
--

DROP TABLE IF EXISTS `shopping_lists_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_lists_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_lists_seq`
--

LOCK TABLES `shopping_lists_seq` WRITE;
/*!40000 ALTER TABLE `shopping_lists_seq` DISABLE KEYS */;
INSERT INTO `shopping_lists_seq` VALUES (301);
/*!40000 ALTER TABLE `shopping_lists_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocks` (
  `stockID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `inventoryID` int NOT NULL,
  `itemID` int NOT NULL,
  `quantity` int NOT NULL,
  `expirationDate` varchar(255) DEFAULT NULL,
  UNIQUE KEY `stockID_UNIQUE` (`stockID`),
  KEY `inventoryID_idx` (`inventoryID`),
  KEY `accountID_stocks_idx` (`accountID`),
  KEY `itemID_stocks_idx` (`itemID`),
  CONSTRAINT `accountID_stocks` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE,
  CONSTRAINT `inventoryID_stocks` FOREIGN KEY (`inventoryID`) REFERENCES `inventories` (`inventoryID`) ON DELETE CASCADE,
  CONSTRAINT `itemID_stocks` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=361 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (352,1352,202,1102,100,'2023-06-01'),(353,1352,202,1103,10,'2023-04-30'),(354,1352,203,1104,1,'2023-04-30'),(355,1353,204,1105,5,'2023-04-30'),(356,1353,204,1106,20,'2023-04-20'),(357,1353,204,1107,1,'2023-05-05'),(358,1354,205,1108,10,'2023-06-01'),(359,1354,206,1109,15,'2023-04-01'),(360,1354,206,1111,10,'2024-01-01');
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks_seq`
--

DROP TABLE IF EXISTS `stocks_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocks_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks_seq`
--

LOCK TABLES `stocks_seq` WRITE;
/*!40000 ALTER TABLE `stocks_seq` DISABLE KEYS */;
INSERT INTO `stocks_seq` VALUES (451);
/*!40000 ALTER TABLE `stocks_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `storeID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`storeID`),
  UNIQUE KEY `storeID_UNIQUE` (`storeID`),
  KEY `accountID_stores_idx` (`accountID`),
  CONSTRAINT `accountID_stores` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=454 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (402,1352,'Walmart','11111 Blue Rd'),(403,1352,'Target','11111 Red Rd'),(404,1353,'Kroger','22222 Blue Rd'),(405,1354,'Costco','33333 Red Rd'),(406,1354,'Local','');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores_seq`
--

DROP TABLE IF EXISTS `stores_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores_seq`
--

LOCK TABLES `stores_seq` WRITE;
/*!40000 ALTER TABLE `stores_seq` DISABLE KEYS */;
INSERT INTO `stores_seq` VALUES (551);
/*!40000 ALTER TABLE `stores_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-10 12:26:46
