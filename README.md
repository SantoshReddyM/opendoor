# opendoor

This contains a simple java servlet handling the GET requests for listings present in listings.csv

API:
GET /listings?min_price=100000&max_price=200000&min_bed=2&max_bed=2&min_bath=2&max_bath=2

min_price: The minimum listing price in dollars.
max_price: The maximum listing price in dollars.
min_bed: The minimum number of bedrooms.
max_bed: The maximum number of bedrooms.
min_bath: The minimum number of bathrooms.
max_bath: The maximum number of bathrooms.

Implementation : Read each record from the csv file and check if the listing matches the given input parameters. If so, create a json object and add it to the result.

The GET API is deployed on Heroku: http://aqueous-earth-6917.herokuapp.com/

Improvements :

1. Can definitely make the UI prettier.
2. Parameter validation can be made strict. Appropriate error/warning messages can be shown.
3. Write unit tests
4. Improve the performance by doing a bulk read of listings from the csv file in a single read operation.
