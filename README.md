### Fuel consumption API

To test the package, Swedbank.postman_collection.json can be used together with Postman. To import data I've added example file - FuelBulk.txt. API currntly only understands files with JSON in each line. Imports are done by iterating each line of the file and importing reports. 

For storage, custom FileSystem adapter is implemented. It's not SQL like. To select data, conditions can be used (entitymanager/condition). For now only necessary ones are implemented. Data is stored into files in storage folder. Each entity has its own file with json inside it. To store data, custom made command bus is used together with custom made entity manager. Idea was to make it ORM like, where persisting objects insterts them or saves them to database and repositories are used to retrieve data from the file. 

Storage adapter can be changed and there is a resolver implemented, that can adapt to new storage adapters. Config is located in resources/storage.properties.

