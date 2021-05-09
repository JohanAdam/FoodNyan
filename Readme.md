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