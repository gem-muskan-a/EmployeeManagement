# Employee Management

My first Spring Boot CRUD application which I have created using spring boot, JPA Repository, mySQL database, swagger documentation, exception handling.


## Features

- GET Api to fetch all employees details.
- GET Api to fetch employee detail by empID.
- GET Api to fetch all employees details by deptID.
- POST Api to add employee details.
- PUT Api to update employee details.
- DELETE Api to soft delete employee.
- GET Api to fetch all department details.
- GET Api to fetch department detail by deptID.
- POST Api to add department details.
- PUT Api to update department details.
- DELETE Api to soft delete department.


## API Reference

### Get all employees ("/employee")

```http
  GetMapping("/")
```

### Get employee by id ("/employee")

```http
  GetMapping("/{id}")
```

| Parameter | Type     | Description                                       |
| :-------- | :------- |:--------------------------------------------------|
| `id`      | `int` | **Required**. Id of employee to fetch the details |

### Get employee by deptId ("/employee")

```http
  GetMapping("/dept/{deptId}")
```

| Parameter | Type     | Description                                                                     |
|:----------| :------- |:--------------------------------------------------------------------------------|
| `deptId`  | `int` | **Required**. Id of department to fetch the employee list within the department |

### Add employee ("/employee")

```http
  PostMapping("/")
```

| Parameter | Type     | Description                        |
| :-------- | :------- |:-----------------------------------|
| `employee`      | `employee` | **Required**. Employee to be added |

### Update employee ("/employee")

```http
  PutMapping("/{id}")
```

| Parameter | Type     | Description                                  |
| :-------- | :------- |:---------------------------------------------|
| `id`      | `int` | **Required**. Id of employee to be updated   |
| `employee`  | `employee` | **Required**. Employee details to be updated |

### Delete employee ("/employee")

```http
  DeleteMapping("/{id}")
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of employee to be deleted |

### Get all department ("/department")

```http
  GetMapping("/")
```

### Get department by id ("/department")

```http
  GetMapping("/{id}")
```

| Parameter | Type     | Description                                       |
| :-------- | :------- |:--------------------------------------------------|
| `id`      | `int` | **Required**. Id of department to fetch the details |

### Add department ("/department")

```http
  PostMapping("/")
```

| Parameter | Type     | Description                          |
| :-------- | :------- |:-------------------------------------|
| `department`      | `department` | **Required**. Department to be added |

### Update department ("/department")

```http
  PutMapping("/{id}")
```

| Parameter | Type     | Description                                    |
| :-------- | :------- |:-----------------------------------------------|
| `id`      | `int` | **Required**. Id of department to be updated     |
| `department`  | `department` | **Required**. Department details to be updated |

### Delete department ("/department")

```http
  DeleteMapping("/{id}")
```

| Parameter | Type     | Description                                  |
| :-------- | :------- |:---------------------------------------------|
| `id`      | `int` | **Required**. Id of department to be deleted |


## 🔗 Swagger Documentation Link

http://localhost:8083/swagger-ui/index.html

## 🔗 Repository Link


## Developers

- Muskan Agrawal


