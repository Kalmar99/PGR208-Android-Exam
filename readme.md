## PGR208 - Android Programming

Exam in android programming


### Known bugs
* app crashes when it cant find data
  - Solution: Add try again button
* app crashes if it cant parse the json it gets (Should never happen but need a failsafe)
* If user closes app before db downloading is done it will not get all results the next time the app is opened

### Improvements to be made
* In FeatureAdapter: refactor so the onClick of location Icon point to MainActivity.
