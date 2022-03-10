# QA - Hobby/Interests Project (Soft Drinks)
By Adil Akbarali
- [Git Repository](https://github.com/adilakbarali/QAC-Project2)
- [Jira](https://adil-akbarali.atlassian.net/jira/software/projects/QP2/boards/8/roadmap)
- [Presentation on Google Slides](https://docs.google.com/presentation/d/17kE0qA4YmA7KTYE4sjBB96C6c6k2YuAc5_gByfpSDUg/edit?usp=sharing)
- [Presentation in PDF Format](documentation/presentation-project2.pdf)

This project was undertaken to meet the specification underlined by QA as part of training. The project requires the creation of a Database using MySQL, which stores data about a particular interest or hobby, as well as the development of a Java application using the Spring Boot framework, thus producing an API of which a front-end (HTML + JavaScript) are then used to interact with this. Unlike the prior tackled project, there is no template to work from, and instead all files are made from scratch. The interest of choice here was Soft Drinks.

## Specification & Tools Required

As highlighted in the specification, we are required to use the following:

- Kanban Board: Jira
- Database: SQL Server
- Programming language: Java (unless stated otherwise)
- Front-end: HTML, CSS and JavaScript
- Integration Tests: MockMVC
- Version Control: Git

## Planning (Kanban in Jira & Git with GitHub)

The Kanban board on Jira can be found [here.](https://adil-akbarali.atlassian.net/jira/software/projects/QP2/boards/8/roadmap)
Do note that the board is Private Access only - Those with permissions shared to them directly will be able to view this.

The board consists of four epics, of which were used to split the tasks up into manageable sized chunks, of which all relate together for the epic. These tasks consisted of user stores, as well as child problems, and some of these were linked to each other

![](https://i.gyazo.com/765274ff6e1f92cd0a801ed4674704ac.png)

Each of these user stories were assigned story points, as well as their child issues as estimates of time for each of the tasks undertaken. An example of blockers is shown below:

![](https://i.gyazo.com/eefe410a3b4f00f8e6479ea59e444a16.png)

The general guidance for the project was to create two different repositories, one for the back-end implementation, and another for the front-end implementation. However one of the solutions to the problem I encountered early with the Fetch API was to use the static folder from within the back-end folder structure, as running the Spring Boot application pulls up the files in the static folder to localhost:8080, allowing us to interact with the webpage from there, and direct the requests easier within JavaScript.

With this in mind, I ended up initialising only the one repository, and had relevant feature branches for both the back-end and the front-end:

![](https://i.gyazo.com/2489ba876f6eeeb4d095bd5ef336ee25.png)

After configuring the repository and setting up an initial commit to the main branch, the same commit was made into a new dev branch, and from there each new branch followed the feature-branch model, where development was done on a relevant branch, and merged onto dev. Once done entirely with the project, dev is then merged into main. Below is an image of the network graph, showing the commits and merges. Do note that at the time of writing this README file, the documentation branch is still undergoing development, and so the final merge to dev, and dev to main is not yet done, but the repository will have the final merge to main at the time of reading this file.

![](https://i.gyazo.com/2cefbb611f3edbefa1ccdf1d4fa0779c.png)

## Database (SQL Server)

Two instances of a database are used for the purpose of this project. The primary instance is through MySQL, as this will manage the core data thats being used within the application, and the other is h2, an in-storage database for Java, of which was used for testing. The schema which is used for h2 differs slightly in format due to how h2 works, and the schema for this can be found [here,](src/main/resources/softdrink-schema.sql) with the accompanying data file [here.](src/main/resources/softdrink-data.sql)

The data file used for the h2 database is the exact same as the one we use for MySQL, however as the schema differs slightly, you can find the schema for MySQL in the documentation folder, or optionally click [here.](documentation/softdrink-mysql-schema.sql)

The table layout is as displayed below:

![](https://i.gyazo.com/2ce9d7a545bac71cd31bb2ec70b13512.png)

## Java & Testing (MockMVC)

Using the Spring Boot Framework within Eclipse, I was able to create the API, allowing for a front-end interface to interact with the database on the back end. The mapping for the APIs was assigned through the [SoftDrinkController](src/main/java/com/qa/project2/web/SoftDrinkController.java) class. An example of one of these mappings is shown below:

![](https://i.gyazo.com/ceebba3df817a0a5929617a152656268.png)

Due to time constraints we were only expected to carry out integration testing through the use of MockMVC. This tool allows us to test server-side methods by performing requests and catching the responses from the server. A simple example of one of these tests is shown below:

![](https://i.gyazo.com/f7364babd8751d4ff7dfaa60576cd91a.png)

Through integration tests, I was able to achieve a testing coverage of 83.8%:

![](https://i.gyazo.com/eafe5decb321ff6f89e0c05ba41f6f08.png)

## Front-End (HTML, CSS & JS)

To be able to perform functions on the database, a front end was required that utilises the APIs created using the Spring Boot framework. The most common way of accessing an API is through a web browser, and so HTML is required to design a web page, alongside CSS for styling, and JavaScript to perform fetch requests on the API, as well as the transferring of data, and additional formatting of the web page. In addition to the core functions provided by these, I also used Bootstrap, which provided some fancier buttons, as well as a style called Jumbotron, giving access to a quick and easy large title on the page. I also used [Gradient Animator](https://www.gradient-animator.com/) which generated some CSS to provide an animated gradient background to the web page.

The webpage is simplistic, with a title, small description and a handful of buttons to start, giving the user some options to interact with this:

![](https://i.gyazo.com/eb8b31206633823605192c88e82b408d.png)

From here, the user can click the relevant buttons to start interacting. Below is an example of pressing the Get All Records button:

![](https://i.gyazo.com/a42af31962abc30272f544d67a5dd3c1.png)

From here, the user is presented with other options alongside their data, being Update and Delete, which allows the user to directly update or delete the selected data entry. They may also create an entry or search an entry by ID number by utilising the buttons at the top of the screen. Once the user creates or updates an entry, the table is cleared and they are presented with the specific entry that was either created or updated.

JavaScript provides the functionality of hiding and showing certain elements on the page when buttons are clicked, as well as the Fetch API and formatting for the data, whether it be to output to the user or input to the database.