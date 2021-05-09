domain :
- Domain model

data :
- Data model

presentation/app :
- Presentation model

Calling Flow :
Viewmodel -> UseCase -> Repository -> Network

Return response :
Network		-> Repository 	-> Usecase 	    -> Viewmodel            -> Activity/Fragment
DataModel 	-> DomainModel 	-> DomainModel 	-> PresentationModel    -> PresentationModel

Naming :
Domain model > XXXEntity
Data model > XXXDataModel
Presentation model > XXXBinding

Api Usage :
Restaurant List : https://raw.githubusercontent.com/JohanAdam/restaurant_dummy/master/db.json
When sending comment with text : http://apitest.thelorry.com/test/true
When try to sending comment without text OR empty : http://apitest.thelorry.com/test/false
When try to load more comment : http://apitest.thelorry.com/test/400
