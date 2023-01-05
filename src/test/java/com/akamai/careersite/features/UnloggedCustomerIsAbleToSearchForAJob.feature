@tag2
Feature: Unlogged customer is able to search for a job

  Scenario Outline: Search for a job matching given criteria
    Given Customer is on akamai career portal
    When Customer specifies "<Find Your Career>"
    And Filter by "<country>"
    Then Any job offers are found

    Examples: 
      | Find Your Career                             | country |
      | Senior Software Development Engineer in Test | Poland  |
