# Big-Data
We review MapReduce techniques for parallel processing, Hadoop, an open source framework for running MapReduce on Internet scale problems and HDFS, Hadoop's Distributed File System. We teach Spark which emerged as the most important big data processing framework. We touch on tools that provide SQL-like access to unstructured data like Hive. 

## A1
Uses the hadoop framework to find the average temperature in a dataset of recoreds.

## A2
The script sets up a Spark session and trains a ridge regression model with training data. It uses the model to predict an example data point's value, then prints the prediction, model weights, and intercept. Finally, it ends the Spark session. This is a basic machine learning task using Spark.

## A3
This script is written in Apache Pig, a platform for analyzing large data sets. It starts by loading a CSV file named 'AB_NYC_2019.csv' into a dataset named clean_data with specified schema.  

For Question 1, it filters the dataset for listings with more than 10 minimum nights, more than 10 reviews, and last reviewed in 2018 or 2019.  

For Question 2, it groups the filtered data by neighbourhood_group, calculates the average price and average availability days, sorts by average price in descending order, and then stores the result in 'AirBnB_neighbourhood'.  

For Question 3, it groups the filtered data by room_type, finds the cheapest listing for each room type, and then outputs this information.

