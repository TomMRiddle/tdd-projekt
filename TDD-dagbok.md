## Wed Jan 8
### Kelley Skolos
- worked on UserTest and User

### Victor Ek
- Added Activity constructor and getters
- throwException tests

## Thu Jan 9
### Kelley Skolos
- User test and User hashmap

### Victor Ek
- Changed id to be in activity class
- Added exception tests
- cleaned up stuff
- end of day 2

## Fri Jan 10
### Kelley Skolos
- updating

### Victor Ek
- added tests for total distance, average distance, fitnessScore. started testing printActivities
- Merge remote-tracking branch 'origin/User' into User
- completed test for printActivities

## Mon Jan 13
### Victor Ek
- Merge branch 'refs/heads/User'
- cleaned ignored files
### Kelley Skolos
- Wrote getActivityById
- Updated lineSeparator

### Victor Ek
- added printActivityById and deleteActivityById
- Merge branch 'refs/heads/newbranch2'
- app with main, test for createUser

### Kelley Skolos
- created testCreateActivity
- Updated tests for User and converted Activity Duration input to Duration instead of String

## Tue Jan 14
### Kelley Skolos
- Changed the formatting of the ByteArrayInputStream, corrected the order in App.java createActivity method, disabled user.addActivity within method.
- Corrected and updated formatting of tests in testActivity with toString method
- Created tests for asserting activities exist, activated the menu in App.java, translated some text, osv.
- Created printDetails method in App and wrote tests for HasActivity

### Victor Ek
- moved setup from beforeeach to beforeall

## Thu Jan 16
### Victor Ek
- Merge remote-tracking branch 'origin/TestApp-createActivity-alternative'
- renamed activity to record
- More renaming of activity to record. removed App and TestApp
- Slowly refactoring to green. Finished addRecord, hasRecord, getRecordById, printRecords.
- updated tests to use mock of fileStorage: hasTotalDistance, printsDetailsWhenGivenIdExist, throwsExceptionWhenRecordIdNotFound, deleteRecordWhenGivenId, throwsExceptionWhenDeletingNonExistingRecord. refactored corresponding code to use fileStorage. except for getTotalDistance is yet to be refactored

## Fri Jan 17
### Victor Ek
- changed test and refactored code for averagedistance to use fileStorage mock
- changed test and refactored code for totalDistance to use fileStorage mock
- changed test and refactored code for fitnessScore to use fileStorage mock
- added tests for getFilteredRecords

## Sun Jan 19
### Victor Ek
- removed all try catch blocks, simplified code
- implemented and refactored printRecordsFilteredByDistance
- 100% code coverage achieved (added JaCoCo exclude for FileStorage dummy)