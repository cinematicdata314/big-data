from pyspark.sql import SparkSession
from pyspark.ml.regression import LinearRegression
from pyspark.ml.linalg import Vectors

#starting spark
spark_session = SparkSession.builder.appName('RidgeRegression').getOrCreate()

#load data
data = spark_session.read.format("libsvm").load("train.txt")

#train and fit the model
ridge_regression = LinearRegression(maxIter=100, regParam=0.5, elasticNetParam=0, fitIntercept = True)
ridge_model = ridge_regression.fit(data)

#example of prediction
eg_prediction = [32.9, 74, 257.50, 70.00, 40.8, 132.4, 108.5, 107.1, 59.3, 42.2, 24.6, 35.7, 30.0, 25.9]
df_prediction = spark_session.createDataFrame([(Vectors.dense(eg_prediction),)], ["features"])
prediction = ridge_model.transform(df_prediction)

#print outputs
prediction.select("Features", "Prediction").show(truncate = False)
print("Model Weights: ", ridge_model.coefficients)
print("Intercept: ", ridge_model.intercept)

#stop spark
spark_session.stop()

