# My Personal Project

## Do you relate with any of the following?
- experience chronic and/or fluctuating periods of pain or discomfort
- frequently use many remedial services from pain medication to massage therapists to acupuncture
- find it difficult to track the long-term severity of a chronic illness


## Then you need Symmer.

*Symmer* is a symptom tracker for those want a centralized space for tracking their chronic symptoms. 

This project was born out of my own experiences with chronic joint pain, ranging from a TMJ 
disorder to persistent knee and back pain. I wanted to create a centralized location to track
how my discomfort levels were day-to-day, and also note the methods I used to try and combat this 
discomfort. I hope this application can help those struggling with any kind of unwanted symptoms
by giving them a space to track the ups and downs.

## User Stories
Adding Logs:
- As a user, I want to be able to add a symptom to my symptom log
  that includes the following (whose existece is *not* user-entered):
    - the symptom location
    - the symptom sensation (i.e. burning, numb, aching, sharp)
    - the symptom severity (from 1-5)
    - the symptom duration
    - the date of the symptom occurrence 

- As a user, I want to be able to add a remedy to my remody log that includes
  - the location that the remedy is for (must be a location present as a symptom also)
  - the type of remedy it is (i.e hot/cold pack, pain meds, chiropractor)
  - the date that the remedy was used/applied

Viewing Logs:
- As a user, I want to be able to view my previous symptom logs
- As a user, I want to be able to view my previous remedy logs

Editing Logs:
- As a user, I want to be able to edit a symptom log
- As a user, I want to be able to edit a remedy log

Deleting Logs:
- As a user, I want to be able to delete a symptom log
- As a user, I want to be able to delete a remedy log

Saving Logs:
- As a user, I want to be able to save my symptom logs
- As a user, I want to be able to save my remedy logs

Loading Logs:
- As a user, I want to have the option to reload previously saved symptom logs
- As a user, I want to have the option to reload previously saved remedy logs

## Phase 4: Task 2
Mon Mar 28 17:07:03 PDT 2022
A symptom entry has been added.
Mon Mar 28 17:07:04 PDT 2022
A symptom entry has been added.
Mon Mar 28 17:07:10 PDT 2022
Symptom entry 1 has been deleted.
Mon Mar 28 17:07:17 PDT 2022
A remedy entry has been added.
Mon Mar 28 17:07:18 PDT 2022
A remedy entry has been added.
Mon Mar 28 17:07:19 PDT 2022
A remedy entry has been added.
Mon Mar 28 17:07:27 PDT 2022
Remedy entry 3 has been deleted.
Mon Mar 28 17:07:37 PDT 2022
A symptom entry has been added.

## Phase 4: Task 3
- add exceptions for human error
  - have an abstract InvalidInputException with subclasses for invalid:
    - locations
    - sensations
    - severities
    - durations
    - dates
    - remedies
- separate gui code from ui code
  - have separate classes for each different task:
    - add logs
    - delete logs
    - view logs
    - home panel
- reduce code duplication in gui by extracting helper methods
    - can extract helper from addSymptomLogGUI and addRemedyLogGUI
    - can extract helper from deleteSymptomLogGUI and deleteRemedyLogGUI
- change LOCATIONS, SENSATIONS, SEVERITY, and REMEDIES final lists to separate Enums