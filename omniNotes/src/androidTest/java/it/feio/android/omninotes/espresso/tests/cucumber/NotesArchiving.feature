Feature: Notes archiving
  Scenario: Archive an existing note
    Given user has at least one note available on the list
    When user swipe on the note item
    Then "Note archived" message is visible on the top of the screen
    And "1 archived" message with "Undo" button is visible at the bottom of the screen
    And arichved note is not visible on the list
    When user opens a drawer
    And select "Archive" item
    Then user goes to "Archive" notes list
    And arichived note is visible in the list