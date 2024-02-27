
WITH random_values AS (
  SELECT
    generate_series(1, 1000) AS id,
    md5(random()::text) AS title,
    md5(random()::text) AS description
)
-- Insert random data into todos table
INSERT INTO todos ( title, description)
SELECT  title, description
FROM random_values;

