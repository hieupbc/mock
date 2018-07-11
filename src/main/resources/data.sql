INSERT INTO `bus` (`bus_name`, `description`)
VALUES (1, "Consequat Purus Associates"), (2, "Ac Eleifend Vitae Associates"), (3, "Aliquam Consulting"),
  (4, "Egestas Fusce Aliquet LLP"), (5, "Conubia Nostra Corp."), (6, "Vulputate Limited"),
  (7, "Pellentesque Eget Foundation"), (8, "Aliquam Ultrices Inc."), (9, "Varius Inc."), (10, "Duis At Limited");
INSERT INTO `bus` (`bus_name`, `description`)
VALUES (11, "Vitae Ltd"), (12, "Consequat Auctor Industries"), (13, "Dolor Donec PC"), (14, "Lorem Associates"),
  (15, "Lobortis Mauris Suspendisse Inc."), (16, "Cum Sociis Natoque Corporation"), (17, "Nunc Sed Company"),
  (18, "Turpis Egestas Aliquam Inc."), (19, "Tellus Phasellus Elit Inc."), (20, "Libero Integer Incorporated");

INSERT INTO `Station` (`name`)
VALUES ("La Unión"), ("Gijzelbrechtegem"), ("Mattersburg"), ("Bussolengo"), ("Essex"), ("Missoula"), ("Naninne"),
  ("Kapolei"), ("Arrah"), ("Nijlen");
INSERT INTO `Station` (`name`)
VALUES ("Vinh"), ("Hanoi"), ("HCM"), ("Court"), ("Lowell"), ("Waterloo"), ("Mansfield"), ("Weyburn"), ("Barghe"),
  ("Richmond");

INSERT INTO `route` (`id`, `duration`, `price`, `route_from`, `route_to`) VALUES
  (1, 123, 61, 1, 3),
  (2, 110, 69, 2, 4),
  (3, 115, 44, 3, 5),
  (4, 94, 65, 4, 6),
  (5, 96, 41, 5, 7),
  (6, 97, 64, 6, 8),
  (7, 82, 62, 7, 9),
  (8, 113, 65, 8, 10),
  (9, 130, 48, 9, 11),
  (10, 147, 54, 10, 12),
  (11, 83, 61, 11, 13),
  (12, 111, 52, 12, 14),
  (13, 94, 63, 13, 15),
  (14, 122, 64, 14, 16),
  (15, 124, 53, 15, 17),
  (16, 135, 50, 16, 18),
  (17, 118, 64, 17, 19),
  (18, 109, 52, 18, 20),
  (19, 135, 51, 19, 2),
  (20, 150, 56, 20, 2),
  (21, 120, 30, 1, 2),
  (22, 120, 30, 1, 4),
  (23, 120, 30, 1, 5),
  (24, 120, 30, 1, 6),
  (25, 120, 30, 1, 7);


INSERT INTO `journey` (`id`, `depart_date`, `depart_time`, `bus`, `route_id`) VALUES
  (1, '2019-02-14', '08:22:10', 1, 3),
  (2, '2019-05-01', '11:14:01', 5, 3),
  (3, '2018-08-31', '10:56:45', 11, 20),
  (4, '2019-04-12', '01:19:23', 5, 12),
  (5, '2018-07-26', '18:47:50', 15, 7),
  (6, '2019-04-01', '15:16:56', 13, 18),
  (7, '2019-04-06', '15:36:53', 20, 17),
  (8, '2018-11-10', '19:22:09', 10, 16),
  (9, '2019-05-27', '18:02:56', 5, 15),
  (10, '2018-07-30', '22:39:19', 5, 8),
  (11, '2018-06-22', '15:01:12', 3, 1),
  (12, '2019-04-24', '07:27:24', 3, 8),
  (13, '2018-06-14', '21:21:17', 13, 8),
  (14, '2019-05-15', '09:14:58', 20, 15),
  (15, '2019-04-21', '16:17:37', 12, 6),
  (16, '2018-12-07', '10:49:23', 13, 6),
  (17, '2018-10-18', '10:54:19', 7, 8),
  (18, '2018-12-16', '11:06:39', 1, 19),
  (19, '2019-03-12', '19:14:19', 4, 6),
  (20, '2019-05-13', '19:41:45', 4, 5),
  (21, '2019-01-06', '23:05:30', 11, 9),
  (22, '2019-03-26', '23:51:07', 11, 1),
  (23, '2018-11-01', '06:17:28', 17, 17),
  (24, '2018-07-19', '23:33:38', 8, 19),
  (25, '2019-06-15', '03:55:57', 13, 4),
  (26, '2018-12-11', '17:37:40', 16, 18),
  (27, '2018-10-17', '04:42:13', 19, 11),
  (28, '2019-06-10', '22:33:42', 5, 3),
  (29, '2018-08-25', '15:05:22', 18, 4),
  (30, '2019-04-02', '08:22:07', 1, 6),
  (31, '2019-04-08', '04:42:47', 11, 13),
  (32, '2019-06-09', '18:44:11', 6, 16),
  (33, '2018-06-08', '20:05:39', 1, 9),
  (34, '2018-08-27', '06:28:17', 7, 3),
  (35, '2018-07-08', '04:29:43', 19, 14),
  (36, '2018-11-12', '10:43:35', 2, 3),
  (37, '2018-09-14', '04:27:22', 2, 19),
  (38, '2019-04-25', '01:44:34', 11, 16),
  (39, '2018-12-29', '08:33:25', 10, 1),
  (40, '2018-06-24', '01:26:10', 2, 19),
  (41, '2018-06-22', '20:52:15', 19, 1),
  (42, '2018-06-22', '17:24:44', 5, 1),
  (43, '2019-03-06', '14:11:15', 14, 16),
  (44, '2019-04-25', '14:39:38', 16, 18),
  (45, '2018-10-24', '13:09:03', 18, 4),
  (46, '2019-05-30', '11:53:51', 20, 12),
  (47, '2019-02-13', '10:45:44', 5, 18),
  (48, '2019-03-06', '20:50:30', 7, 8),
  (49, '2018-11-21', '14:03:09', 20, 13),
  (50, '2019-04-08', '02:44:33', 12, 19),
  (51, '2018-07-21', '10:03:09', 2, 21),
  (52, '2018-07-21', '11:03:09', 13, 21),
  (53, '2018-07-21', '12:03:09', 12, 21),
  (54, '2018-07-21', '13:03:09', 15, 21),
  (55, '2018-07-21', '14:03:09', 6, 21);
INSERT INTO `journey` (`depart_date`, `depart_time`, `bus`, `route_id`)
VALUES ("18/06/22", "15:01:12", 3, 1), ("19/04/24", "07:27:24", 3, 8), ("18/06/14", "21:21:17", 13, 8),
  ("19/05/15", "09:14:58", 20, 15), ("19/04/21", "16:17:37", 12, 6), ("18/12/07", "10:49:23", 13, 6),
  ("18/10/18", "10:54:19", 7, 8), ("18/12/16", "11:06:39", 1, 19), ("19/03/12", "19:14:19", 4, 6),
  ("19/05/13", "19:41:45", 4, 5);
INSERT INTO `journey` (`depart_date`, `depart_time`, `bus`, `route_id`)
VALUES ("19/01/06", "23:05:30", 11, 9), ("19/03/26", "23:51:07", 11, 1), ("18/11/01", "06:17:28", 17, 17),
  ("18/07/19", "23:33:38", 8, 19), ("19/06/15", "03:55:57", 13, 4), ("18/12/11", "17:37:40", 16, 18),
  ("18/10/17", "04:42:13", 19, 11), ("19/06/10", "22:33:42", 5, 3), ("18/08/25", "15:05:22", 18, 4),
  ("19/04/02", "08:22:07", 1, 6);
INSERT INTO `journey` (`depart_date`, `depart_time`, `bus`, `route_id`)
VALUES ("19/04/08", "04:42:47", 11, 13), ("19/06/09", "18:44:11", 6, 16), ("18/06/08", "20:05:39", 1, 9),
  ("18/08/27", "06:28:17", 7, 3), ("18/07/08", "04:29:43", 19, 14), ("18/11/12", "10:43:35", 2, 3),
  ("18/09/14", "04:27:22", 2, 19), ("19/04/25", "01:44:34", 11, 16), ("18/12/29", "08:33:25", 10, 1),
  ("18/06/24", "01:26:10", 2, 19);
INSERT INTO `journey` (`depart_date`, `depart_time`, `bus`, `route_id`)
VALUES ("18/06/22", "20:52:15", 19, 1), ("18/06/22", "17:24:44", 5, 1), ("19/03/06", "14:11:15", 14, 16),
  ("19/04/25", "14:39:38", 16, 18), ("18/10/24", "13:09:03", 18, 4), ("19/05/30", "11:53:51", 20, 12),
  ("19/02/13", "10:45:44", 5, 18), ("19/03/06", "20:50:30", 7, 8), ("18/11/21", "14:03:09", 20, 13),
  ("19/04/08", "02:44:33", 12, 19);

INSERT INTO `seat` (`id`) VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);
INSERT INTO `seat` (`id`) VALUES (11), (12), (13), (14), (15), (16), (17), (18), (19), (20);
INSERT INTO `seat` (`id`) VALUES (21), (22), (23), (24), (25), (26), (27), (28), (29), (30);
INSERT INTO `seat` (`id`) VALUES (31), (32), (33), (34), (35), (36), (37), (38);

INSERT INTO `users` (`name`, `email`, `password`, `enabled`)
VALUES ("Robert", "auctor.non@lacusNulla.co.uk", "Chloe Ellis", 1),
  ("Mark", "ultricies@sem.co.uk", "Erica Dominguez", 1),
  ("Kane", "ac.turpis.egestas@atfringilla.ca", "Alexis Colon", 1),
  ("Basil", "Vivamus.rhoncus.Donec@Quisqueaclibero.net", "Britanney Sykes", 1),
  ("Finn", "dui.Cum@pede.org", "Neve Boone", 1), ("Martin", "Nunc.mauris@variuseteuismod.org", "Lila Riley", 1),
  ("Kieran", "enim@Inornaresagittis.ca", "Quon Miles", 1), ("Hoyt", "nisi@massa.net", "Vielka Duran", 1),
  ("Vladimir", "euismod.est.arcu@urna.ca", "Faith Porter", 1), ("Ryan", "dictum@molestie.com", "Destiny Glass", 1);
INSERT INTO `users` (`name`, `email`, `password`, `enabled`) VALUES ("Jonah", "fames@non.com", "Wendy Gilbert", 1),
  ("Zephania", "lacus.Mauris.non@gravidasagittisDuis.edu", "Jenna Lancaster", 1),
  ("Herman", "consequat.lectus.sit@non.org", "Illiana Barron", 1),
  ("Colin", "Aliquam.adipiscing.lobortis@dictum.co.uk", "Kylee Wall", 1),
  ("Amal", "dolor.nonummy.ac@orcisem.com", "Maia Faulkner", 1),
  ("Kieran", "Suspendisse.tristique.neque@nislsemconsequat.net", "Kylan Herman", 1),
  ("Slade", "Mauris@magnamalesuada.org", "Kathleen Richard", 1),
  ("Quinlan", "arcu.Vestibulum.ante@Sed.co.uk", "Quinn Lynch", 1), ("Thane", "Quisque@Nam.net", "Carla Floyd", 1),
  ("Gregory", "arcu.Vestibulum@dis.ca", "Germane Fry", 1);
INSERT INTO `users` (`name`, `email`, `password`, `enabled`)
VALUES ("Ray", "Praesent.eu@utaliquam.org", "Tana Middleton", 1),
  ("Akeem", "fringilla@ligulaAenean.com", "Desiree Preston", 1),
  ("Forrest", "sapien.Cras@necmollis.co.uk", "Amena Haney", 1),
  ("Abel", "nunc.interdum@duinecurna.com", "Chantale Stanton", 1),
  ("Cullen", "scelerisque@aliquet.co.uk", "Anne Daniel", 1),
  ("Elliott", "congue.turpis.In@magnaCras.com", "Brenna Parrish", 1),
  ("Upton", "Pellentesque.habitant.morbi@nuncullamcorper.co.uk", "Tamekah Palmer", 1),
  ("Ian", "Suspendisse.commodo@placerat.com", "Maggy Hancock", 1),
  ("Jacob", "velit.Sed.malesuada@massarutrum.co.uk", "Leandra Lara", 1),
  ("Michael", "condimentum@nuncsed.net", "Shafira Fitzgerald", 1);

UPDATE users
set account_balance = 0
where users.id <= 20;
UPDATE users
set account_balance = 100
where users.id > 20;


INSERT INTO `ticket` (`status`, `journey_id`, `seat_id`, `user_id`)
VALUES (1, 1, 1, 1), (1, 1, 2, 4), (1, 1, 4, 4), (1, 1, 6, 4), (1, 1, 8, 4), (1, 1, 10, 2), (1, 1, 12, 2),
  (1, 1, 14, 2), (1, 1, 16, 4), (1, 1, 18, 1);
INSERT INTO `ticket` (`status`, `journey_id`, `seat_id`, `user_id`)
VALUES (1, 1, 20, 4), (1, 1, 22, 2), (1, 1, 24, 5), (1, 1, 26, 5), (1, 1, 28, 3), (1, 1, 30, 4);