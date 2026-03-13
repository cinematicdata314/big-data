# Big Data Processing Projects (Hadoop, Spark, Pig)

This repository contains three projects demonstrating different big data processing frameworks used for large-scale data analysis. These projects showcase practical experience with **Hadoop MapReduce, Apache Spark, and Apache Pig** for distributed data processing and machine learning tasks.

---

# Technologies Used

- Java
- Python
- Hadoop MapReduce
- Apache Spark (PySpark)
- Apache Pig
- Distributed Data Processing
- Machine Learning (Linear Regression)

---

# Project 1 – Hadoop MapReduce: Average Temperature Analysis

## Overview

This project processes weather data using **Hadoop MapReduce** to calculate the **average temperature for each year-month combination**.

The program reads raw weather records, extracts relevant fields, filters invalid measurements, and computes the average temperature.


## Files

- `AvgTemperature.java`
- `AvgTemperatureMapper.java`
- `AvgTemperatureReducer.java`

## How It Works

### Driver

The driver class configures and runs the Hadoop job. It:

- Accepts input and output paths as arguments
- Configures the mapper and reducer classes
- Defines output key/value types
- Launches the MapReduce job


### Mapper

The mapper reads each weather record and extracts:

- Year and month
- Time of observation
- Temperature value
- Data quality code

The mapper filters the data to include only:

- Observations recorded at **13:00**
- Valid temperature values
- Accepted quality codes

The mapper emits key-value pairs in the form:

```
(yearMonth, temperature)
```


### Reducer

The reducer receives all temperature values associated with each year-month key.

Steps performed:

1. Sum all temperature values
2. Count the number of observations
3. Compute the average temperature
4. Output the result

Example output:

```
201901 12
201902 9
201903 15
```

---

# Project 2 – Apache Spark: Ridge Regression Model

## Overview

This project demonstrates training a **Ridge Regression model** using **Apache Spark MLlib**.

The program loads training data in **LIBSVM format**, trains a linear regression model with L2 regularization, and generates a prediction for a sample input.


## File

- `ridge_regression.py`


## Workflow

1. Start a Spark session
2. Load dataset using LIBSVM format
3. Train a Ridge Regression model with parameters:
   - `maxIter = 100`
   - `regParam = 0.5`
   - `elasticNetParam = 0`
4. Create a feature vector for prediction
5. Generate predicted output
6. Print model coefficients and intercept

Example output:

```
Features: [32.9, 74, 257.5, ...]
Prediction: 205.13

Model Weights: [...]
Intercept: 12.45
```

---

# Project 3 – Apache Pig: Airbnb Data Analysis

## Overview

This project analyzes Airbnb listing data using **Apache Pig**, focusing on filtering listings and computing statistics by neighborhood and room type.

Dataset used:

```
AB_NYC_2019.csv
```


## Data Processing Steps

### Query 1 – Data Filtering

Filters listings where:

- `minimum_nights > 10`
- `number_of_reviews > 10`
- `last_review` occurred in **2018 or 2019**

This ensures the analysis focuses on listings with sufficient activity and recent review data.


### Query 2 – Neighborhood Analysis

Listings are grouped by **neighbourhood_group** to calculate:

- Average listing price
- Average yearly availability

Results are sorted by **highest average price**.

Example output:

```
Manhattan, 180.45, 210
Brooklyn, 125.30, 195
Queens, 98.10, 220
```


### Query 3 – Minimum Price by Room Type

Listings are grouped by **room_type** and the lowest priced listing is identified for each category.

Example output:

```
Entire home/apt, 50, Cozy Apartment
Private room, 25, Budget Room
Shared room, 18, Shared Loft
```




