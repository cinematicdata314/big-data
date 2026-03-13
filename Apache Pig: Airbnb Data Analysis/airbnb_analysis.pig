clean_data = LOAD 'AB_NYC_2019.csv' USING PigStorage(',') AS 
    (id: int,
    name: chararray,
    host_id: int,
    host_name: chararray,
    neighbourhood_group: chararray,
    neighbourhood: chararray,
    latitude: float,
    longitude: float,
    room_type: chararray,
    price: int,
    minimum_nights: int,
    number_of_reviews: int,
    last_review: chararray,
    reviews_per_month: float,
    calculated_host_listings_count: int,
    availability_365: int);

--Question 1
filtered_data = FILTER clean_data BY 
    (minimum_nights > 10 AND number_of_reviews > 10 AND 
    (last_review MATCHES '.*2018.*' OR last_review MATCHES '.*2019.*'));

--Question 2
general_neighbourhood_group = GROUP filtered_data BY neighbourhood_group;
general_average = FOREACH general_neighbourhood_group GENERATE 
                group AS neighbourhood_group,
                AVG(filtered_data.price) AS average_price,
                AVG(filtered_data.availability_365) AS available_days;
ordered_neighbourhood_group = ORDER general_average BY average_price DESC;
STORE ordered_neighbourhood_group INTO 'AirBnB_neighbourhood' USING PigStorage(',');

--Question 3
group_room = GROUP filtered_data BY room_type;
min_room_price = FOREACH group_room {
    ordered_amount = ORDER filtered_data BY price ASC;
    total_amount = LIMIT ordered_amount 1;
    GENERATE group AS room_type, total_amount.price, total_amount.name;
};

DUMP min_room_price;


