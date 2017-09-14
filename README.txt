1: build code:  mvn clean install

2: run the application : 
     java -jar target/product-web-service-0.1.0.jar

3:   Run the following curl command to create new product:

     curl  --header "Content-type: application/json" \
     --request POST \
     --data '{
  	"productName": "unique-name-3833729734294724277",
	"productDescription":" simple description"
     }' \
     http://localhost:8080/productwebservice/v1/newproduct
  
  4: Run the following curl command to   update product ( need to replace with the id just created)
  
  
   curl  --header "Content-type: application/json" \
     --request PUT \
     --data '{
  	"productName": "unique-name-38337297342947246788",
	"productDescription":" updated simple description"
     }' \
     http://localhost:8080/productwebservice/v1/6292680d-022d-484a-b0f0-f4f1101bbca4
     
  5:  Run the following command to delete product
  
    
   curl  --header "Content-type: application/json" \
     --request DELETE \
      http://localhost:8080/productwebservice/v1/6292680d-022d-484a-b0f0-f4f1101bbca4
      
  6:  Run the following command to retrieve all products:
  
    curl  --header "Content-type: application/json" \
     --request GET \
      http://localhost:8080/productwebservice/v1/all
      
   7: Run the following command to retrieve one product;
   
    curl  --header "Content-type: application/json" \
     --request GET \
      http://localhost:8080/productwebservice/v1/dd1f0f19-93e1-44d1-b7ac-7700f3df0a75
   
   