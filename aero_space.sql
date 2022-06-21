-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 28 oct. 2021 à 23:42
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `aero_space`
--

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `id` int(11) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `year` int(4) NOT NULL,
  `pages` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `year`, `pages`) VALUES
(11, '11', 'aaabb', 11, 11);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `CIN` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`CIN`, `firstname`, `lastname`, `email`) VALUES
('111', '111', '111', '111'),
('1', '11', '11', '11');

-- --------------------------------------------------------

--
-- Structure de la table `customer`
--

CREATE TABLE `customer` (
  `custID` varchar(6) NOT NULL,
  `custTital` varchar(5) DEFAULT NULL,
  `custName` varchar(30) DEFAULT NULL,
  `custPhoneNo` varchar(13) DEFAULT NULL,
  `custAddress` varchar(30) DEFAULT NULL,
  `custEmail` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `RegDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `customer`
--

INSERT INTO `customer` (`custID`, `custTital`, `custName`, `custPhoneNo`, `custAddress`, `custEmail`, `city`, `province`, `RegDate`) VALUES
('C001', NULL, '', '', '', '', '', NULL, '2021-10-27'),
('C002', NULL, '', '', '', '', '', NULL, '2021-10-27'),
('C003', NULL, '', '', '', '', '', NULL, '2021-10-28'),
('C004', NULL, '', '', '', '', '55', NULL, '2021-10-28'),
('C005', NULL, '', '', '', '', '', NULL, '2021-10-28');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `idevent` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `time` date NOT NULL,
  `startdate` date NOT NULL,
  `finishdate` date NOT NULL,
  `address` varchar(255) NOT NULL,
  `available` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `factures_clients`
--

CREATE TABLE `factures_clients` (
  `id_Fac` varchar(42) NOT NULL,
  `nom_client` varchar(42) NOT NULL,
  `date_fac` date NOT NULL,
  `reglement_fac` varchar(42) NOT NULL,
  `etat_fac` varchar(42) NOT NULL,
  `TVA_fac` int(42) NOT NULL,
  `remise_fac` int(42) NOT NULL,
  `NB_fac` varchar(42) NOT NULL,
  `Totale_fac` int(42) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `factures_clients`
--

INSERT INTO `factures_clients` (`id_Fac`, `nom_client`, `date_fac`, `reglement_fac`, `etat_fac`, `TVA_fac`, `remise_fac`, `NB_fac`, `Totale_fac`) VALUES
('NQCGRX', 'aymen', '2021-09-30', 'Carte', 'Le paiement a ete effectue', 1, 1, 'aymen', 123);

-- --------------------------------------------------------

--
-- Structure de la table `factures_employes`
--

CREATE TABLE `factures_employes` (
  `id_Emp` varchar(42) NOT NULL,
  `nom_emp` varchar(42) NOT NULL,
  `date_fac` date NOT NULL,
  `reglement_fac` varchar(42) NOT NULL,
  `etat_fac` varchar(42) NOT NULL,
  `TVA_fac` int(8) NOT NULL,
  `bonus_fac` int(8) NOT NULL,
  `NB_fac` varchar(42) NOT NULL,
  `Totale_fac` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `factures_employes`
--

INSERT INTO `factures_employes` (`id_Emp`, `nom_emp`, `date_fac`, `reglement_fac`, `etat_fac`, `TVA_fac`, `bonus_fac`, `NB_fac`, `Totale_fac`) VALUES
('RLQXYJ', 'aaa', '2021-09-29', 'pas en core', 'Espece', 1, 12222, '1	', 1);

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `etoile` int(100) NOT NULL,
  `hebergement` varchar(100) NOT NULL,
  `lieu` varchar(100) NOT NULL,
  `Path_image` varchar(255) NOT NULL,
  `Path_video` varchar(255) NOT NULL,
  `chambre` varchar(100) NOT NULL,
  `ch_In` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `nom`, `etoile`, `hebergement`, `lieu`, `Path_image`, `Path_video`, `chambre`, `ch_In`) VALUES
('1262', 'baada222', 252, 'fezf', 'zefze', 'C:UsersBadis KhalsiPictureshotel.PNG', 'C:UsersBadis KhalsiVideosCapturesIonic App - Opera 2021-09-27 20-09-18.mp4', 'Chambre Triple ', 25525),
('EMGLBK', 'baada', 252, 'fezf', 'zefze', 'null', 'null', 'null', 222),
('MXJHFK', 'aymen', 252, 'fezf', 'zefze', 'C:UsersNomPictures999.PNG', 'C:UsersNomDownloadsyt5s.com-JavaFX _ Making Push Notifications.mp4', 'Single/ ', 25525);

-- --------------------------------------------------------

--
-- Structure de la table `managers`
--

CREATE TABLE `managers` (
  `user_id` int(5) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `department` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `managers`
--

INSERT INTO `managers` (`user_id`, `user_name`, `password`, `department`) VALUES
(1, 'elyes', 'elyes', 'admin'),
(13, 'aymen', 'aymen', 'Financial'),
(14, 'badis', 'badis', 'Hotel'),
(15, 'nourhene', 'nourhene', 'Transport'),
(16, 'omar', 'omar', 'Event'),
(17, 'chayma', 'chayma', 'Offer'),
(18, 'client', 'client', 'Client');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `idoffre` varchar(255) NOT NULL,
  `id_reservation` varchar(255) NOT NULL,
  `Titre` varchar(255) NOT NULL,
  `date_validite` date NOT NULL,
  `taux_de_remise` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `Path_image` varchar(255) NOT NULL,
  `Path_video` varchar(255) NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`idoffre`, `id_reservation`, `Titre`, `date_validite`, `taux_de_remise`, `description`, `Path_image`, `Path_video`, `prix`) VALUES
('77', '77', '77', '2021-10-06', '7', '55', '55', '55', 0),
('SNAAYS', '88', '8822', '2021-09-29', '50%\n', '880', 'C:\\Users\\Nom\\Pictures\\999.PNG', 'C:\\Users\\Nom\\Downloads\\Aymen.mp4', 111102),
('UREUBK', '880', '880', '2021-09-29', '10%\n', '880', 'C:\\Users\\Nom\\Pictures\\999.PNG', 'C:\\Users\\Nom\\Downloads\\Aymen.mp4', 1111);

-- --------------------------------------------------------

--
-- Structure de la table `offvsres`
--

CREATE TABLE `offvsres` (
  `Vres` int(11) NOT NULL,
  `Voff` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `offvsres`
--

INSERT INTO `offvsres` (`Vres`, `Voff`) VALUES
(120, 50);

-- --------------------------------------------------------

--
-- Structure de la table `resevation`
--

CREATE TABLE `resevation` (
  `idRes` varchar(255) NOT NULL,
  `idH` varchar(255) NOT NULL,
  `referance` varchar(255) NOT NULL,
  `numv` varchar(255) NOT NULL,
  `idCli` varchar(255) NOT NULL,
  `idevent` varchar(255) NOT NULL,
  `dateValidation` int(11) NOT NULL,
  `etat` int(11) NOT NULL,
  `pos_map` int(11) NOT NULL,
  `prixT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `statistique`
--

CREATE TABLE `statistique` (
  `referencestat` int(11) NOT NULL,
  `nbrpanne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `statistique`
--

INSERT INTO `statistique` (`referencestat`, `nbrpanne`) VALUES
(15, 30);

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `reference` varchar(255) NOT NULL,
  `typee` varchar(255) NOT NULL,
  `availability` varchar(255) NOT NULL,
  `driver` varchar(255) NOT NULL,
  `prix` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transport`
--

INSERT INTO `transport` (`reference`, `typee`, `availability`, `driver`, `prix`, `date`) VALUES
('11', 'Car', '1122', '11', 11, '2021-10-28'),
('11', 'Car', '1122', '11', 11, '2021-10-28');

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `nomv` varchar(255) NOT NULL,
  `numv` varchar(255) NOT NULL,
  `dated` date NOT NULL,
  `datea` date NOT NULL,
  `chauffeur` varchar(255) NOT NULL,
  `depart` varchar(255) NOT NULL,
  `arriver` varchar(255) NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vol`
--

INSERT INTO `vol` (`nomv`, `numv`, `dated`, `datea`, `chauffeur`, `depart`, `arriver`, `prix`) VALUES
('11', '11', '2021-09-29', '2021-10-21', '11', '11', '11', 111122),
('11', '112', '2021-09-29', '2021-10-21', '11', '11', '11', 1111222);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`custID`);

--
-- Index pour la table `factures_clients`
--
ALTER TABLE `factures_clients`
  ADD PRIMARY KEY (`id_Fac`);

--
-- Index pour la table `factures_employes`
--
ALTER TABLE `factures_employes`
  ADD PRIMARY KEY (`id_Emp`);

--
-- Index pour la table `managers`
--
ALTER TABLE `managers`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `managers`
--
ALTER TABLE `managers`
  MODIFY `user_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
