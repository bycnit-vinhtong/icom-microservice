--Category
INSERT INTO category (id, parent_id, name) 
    VALUES (1, null, 'Sport');
    
INSERT INTO category (id, parent_id, name) 
    VALUES (2, null, 'Clothes'); 
    
--Brand
INSERT INTO brand (id, name) 
    VALUES (1, 'Yonex');
    
INSERT INTO brand (id, name) 
    VALUES (2, 'Victor');    
    
-- Product
INSERT INTO product (id, product_code, name, price, currency, description, picture_file_name, picture_uri, brand_id, category_id, color)
	VALUES (1, 'PD1' ,'Badminton bracket1', 50.5, 'USD', 'Very nice bracker', 'picture1.jpg', '/images/picture1.jpg', 1, 1, 'read');
	
INSERT INTO product (id, product_code, name, price, currency, description, picture_file_name, picture_uri, brand_id, category_id, color)
	VALUES (2, 'PD2' ,'Badminton bracket2', 70.5, 'USD', 'Extream nice bracker', 'picture2.jpg', '/images/picture2.jpg', 2, 1, 'green');

INSERT INTO product (id, product_code, name, price, currency, description, picture_file_name, picture_uri, brand_id, category_id, color)
	VALUES (3, 'PD3' ,'TShirt', 30.5, 'USD', 'Very nice bracker', 'picture3.jpg', '/images/picture3.jpg', 1, 2, 'colorful');
	
	
	