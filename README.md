**To test API working**
curl --location 'http://localhost:8081/aws/hello'

**To test API working with path variables**
curl --location 'http://localhost:8081/aws/hello/Praveen'

**To store file on S3 using base 64 format**
curl --location 'http://localhost:8081/aws/api/v1' \
--header 'Content-Type: application/json' \
--data '{
     "fileName": "random_image.jpg",
    "fileType": ".jpg",
    "content": "" // BASE64 format string
}'

**To store file on S3 using multipart format**
curl --location 'http://localhost:8081/aws/api/v1/upload/multipart' \
--form 'file=@"/C:/Users/singh/Downloads/krisna.jpg"'

**To download file from S3**
curl --location 'http://localhost:8081/aws/api/v1/download/krisna.jpg'

**To get S3 file signed URL**
curl --location 'http://localhost:8081/aws/api/v1/krisna.jpg'

**To delete file from S3**
curl --location --request DELETE 'http://localhost:8081/aws/api/v1/krisna.jpg'
