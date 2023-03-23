@tag1
Feature: Customer is notified when no offers match given criteria

  Scenario Outline: Search for a job matching given criteria
    Given Customer is on akamai career portal
    When Customer specifies "<Find Your Career>"
    Then Notification about "<no offers>" found is displayed

    Examples: 
      | Find Your Career | no offers                                     |
      | XXX              | We found 0 jobs based on your search criteria |
      
      
      
  Scenario Outline: Search for a job matching given criteria
    Given Customer is on akamai career portal
    When Customer specifies "<Find Your Career>"
    Then Notification about "<no offers>" found is displayed

    Examples: 
      | Find Your Career | no offers                                     |
      | XXX              | We found 0 jobs based on your search criteria |
      
  Scenario Outline: Search for a job matching given criteria
    Given Customer is on akamai career portal
    When Customer specifies "<Find Your Career>"
    Then Notification about "<no offers>" found is displayed

    Examples: 
      | Find Your Career | no offers                                     |
      | XXX              | We found 0 jobs based on your search criteria |
      
  Scenario Outline: Search for a job matching given criteria
    Given Customer is on akamai career portal
    When Customer specifies "<Find Your Career>"
    Then Notification about "<no offers>" found is displayed

    Examples: 
      | Find Your Career | no offers                                     |
      | XXX              | We found 0 jobs based on your search criteria |
      
