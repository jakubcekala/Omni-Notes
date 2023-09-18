Feature: Notes trashing
  Scenario: Remove a note from the archive
    Given user has at least one note available on the archive list
    And user is on the Main screen of the app
    When user opens a drawer
    And select "Archive" item
    Then user goes to "Archive" notes list
    When user swipe left on the selected note
    Then "Note(s) trashed" message is visible on the top of the screen
    And "1 archived" message with "Undo" button is visible at the bottom of the screen
    And arichved note is not visible on the archived list
    When user opens a drawer
    And select "Trash" item
    Then user goes to "Trash" notes list
    And trashed note is visible in the list