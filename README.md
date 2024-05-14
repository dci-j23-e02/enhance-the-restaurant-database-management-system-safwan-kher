### Assignment: Enhance the Restaurant Database Management System with Advanced Query and Data Entry Features

#### Objective:
Extend the functionality of an existing Restaurant Database Management System by incorporating advanced query capabilities and a new feature for adding dishes. This involves enhancing the repository with custom queries, updating the service layer to utilize these queries, and modifying the controller to handle new endpoints. Additionally, update the Thymeleaf templates to allow users to interact with these new functionalities using Bootstrap 5 for styling, and to provide a user-friendly interface for adding new dishes.

#### Requirements:
1. **Advanced Repository Queries:**
   - Implement custom queries to search for dishes based on price, calories, ingredients, and other criteria.
   - Use JPQL or native SQL queries to achieve complex data retrieval like the most expensive dish, the dish with the least calories, and dishes containing specific ingredients.

2. **Data Entry Feature:**
   - Develop functionality to allow users to add new dishes to the database through a web form.

3. **Service Layer Updates:**
   - Develop methods that utilize the new repository queries to fetch data based on the specified criteria.
   - Add methods to handle the creation and storage of new dish entries.

4. **Controller Enhancements:**
   - Introduce new endpoints in the controller to handle requests that utilize the advanced query methods and the new dish entry form.

5. **View Modifications:**
   - Modify Thymeleaf templates to include interactive forms for searching by various criteria and for adding new dishes.
   - Ensure that the user interface is intuitive and responsive.

#### Tasks:

1. **Repository Custom Queries:**
   - Create a method to retrieve the most expensive dish.
   - Design a query to find the dish with the minimum calories.
   - Develop a method to list dishes based on a specific ingredient.

2. **Data Entry Implementation:**
   - Implement a Thymeleaf form for adding new dishes, including fields for name, price, calories, and ingredients.
   - Add backend support for processing this form and storing the new dish data.

3. **Service Layer Implementation:**
   - Implement service methods that call the newly created repository methods for fetching data as per the user's request.
   - Add a service method to save new dishes to the database.

4. **Controller Adjustments:**
   - Define routes in the controller for each new query type and for the new dish entry form.
   - Ensure these routes handle parameters correctly and pass the appropriate data to the views.

5. **Thymeleaf Template Updates:**
   - Add forms to the home page for searching by various criteria like price, calories, and ingredients.
   - Include a form for adding new dishes, using Bootstrap 5 for styling.
   - Display results and confirmation messages in a user-friendly format.

6. **Testing:**
   - Thoroughly test all new functionalities to ensure they work as expected and integrate seamlessly with existing features.

#### Resources
Below are the Thymeleaf templates needed to handle the various functionalities such as displaying the home page, adding a new dish, and searching for dishes based on different criteria.

### 1. Home Page Template (home.html)

This template will serve as the landing page and include links to other functionalities like adding a new dish and searching for dishes based on various criteria.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Restaurant Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Welcome to the Restaurant Management System</h1>
    <div class="row">
        <div class="col-md-4">
            <a th:href="@{/dishes/add}" class="btn btn-success">Add New Dish</a>
        </div>
        <div class="col-md-4">
            <a th:href="@{/dishes/search}" class="btn btn-primary">Search Dishes</a>
        </div>
    </div>
</div>
</body>
</html>
```

### 2. Add Dish Template (add-dish.html)

This template includes a form for adding a new dish to the system.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Dish</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Add a New Dish</h1>
    <form th:action="@{/dishes/add}" th:object="${dish}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="name" class="form-label">Dish Name</label>
            <input type="text" th:field="*{name}" class="form-control" id="name" placeholder="Enter dish name" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" th:field="*{price}" class="form-control" id="price" placeholder="Enter price" required>
        </div>
        <div class="mb-3">
            <label for="calories" class="form-label">Calories</label>
            <input type="number" th:field="*{calories}" class="form-control" id="calories" placeholder="Enter calories" required>
        </div>
        <div class="mb-3">
            <label for="ingredients" class="form-label">Ingredients</label>
            <input type="text" th:field="*{ingredients}" class="form-control" id="ingredients" placeholder="List ingredients" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Dish</button>
    </form>
</div>
</body>
</html>
```

### 3. Search Dishes Template (search-dishes.html)

This template provides forms for searching dishes based on different criteria such as price, calories, and ingredients.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Dishes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Search Dishes</h1>
    <div class="row">
        <div class="col-md-4">
            <h4>By Ingredient</h4>
            <form th:action="@{/dishes/by-ingredient}" method="get" class="input-group mb-3">
                <input type="text" name="ingredient" class="form-control" placeholder="Ingredient" required>
                <button type="submit" class="btn btn-outline-secondary">Search</button>
            </form>
        </div>
        <div class="col-md-4">
            <h4>Most Expensive Dish</h4>
            <a th:href="@{/dishes/most-expensive}" class="btn btn-primary">Show</a>
        </div>
        <div class="col-md-4">
            <h4>Lowest Calorie Dish</h4>
            <a th:href="@{/dishes/lowest-calories}" class="btn btn-primary">Show</a>
        </div>
    </div>
    <div th:if="${dishes}">
        <h3>Results</h3>
        <div th:each="dish : ${dishes}" class="card mt-2">
            <div class="card-body">
                <h5 class="card-title" th:text="${dish.name}">Dish Name</h5>
                <p class="card-text" th:text="'Price: $' + ${dish.price}">Price</p>
                <p class="card-text" th:text="'Calories: ' + ${dish.calories}">Calories</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```


#### Objectives 
This assignment will challenge students to think critically about database interactions, enhance their understanding of Spring Data JPA, and improve their ability to implement user-driven features in a web application.



