Feature: Notes creating
  Scenario: Create a new Text note
    Given user is on the Main screen of the app
    When user clicks on the FAB button
    Then the fab menu is opened
    And the Text note button is visible
    When user clicks on the Text note button
    Then Note details screen is visible
    And Attachment, Category, Add tags, Share icons are visible in the toolbar
    And the Title field with "Tile" hint is visible
    And the Content field with "Content" hint is visible
    And the Reminder field with "Add reminder" hint is visible
    When user enters a title
    And user enters a content
    And user sets the reminder
    Then all fields are filled and has correct values
    When user goes back to Main screen by clicking on the back button
    Then the created Text note is visible
    And title is visible
    And content is visible
    And update information is visible
    And reminder icon is visible

  Scenario: Create a new Checklist note
    Given user is on the Main screen of the app
    When user clicks on the FAB button
    Then the fab menu is opened
    And the Checklist button is visible
    When user clicks on the Checklist button
    Then Note details screen is visible
    And Attachment, Category, Add tags, Share, More options icon are visible in the toolbar
    And the Title field with "Tile" hint is visible
    And the Items list is visible
    And there is only one item on the list with "New item" hint
    And the Reminder field with "Add reminder" hint is visible
    When user enters a title
    And user add few items to the Items list
    And user sets the reminder
    Then all fields are filled and has correct values
    When user goes back to Main screen by clicking on the back button
    Then the created Checklist note is visible
    And title is visible
    And two first items are visible
    And update information is visible
    And reminder icon is visible

  Scenario: Create an empty Text note
    Given user is on the Main screen of the app
    When user clicks on the FAB button
    Then the fab menu is opened
    And the Text note button is visible
    When user clicks on the Text note button
    Then Note details screen is visible
    And Attachment, Category, Add tags, Share icons are visible in the toolbar
    When does not enter any data
    When user goes back to Main screen by clicking on the back button
    Then the CAN'T SAVE AN EMPTY NOTE message is visible
    And note is not visible

  Scenario: Create an empty Checklist note
    Given user is on the Main screen of the app
    When user clicks on the FAB button
    Then the fab menu is opened
    And the Checklist note button is visible
    When user clicks on the Checklist note button
    Then Note details screen is visible
    And Attachment, Category, Add tags, Share icons are visible in the toolbar
    When does not enter any data
    When user goes back to Main screen by clicking on the back button
    Then the CAN'T SAVE AN EMPTY NOTE message is visible
    And note is not visible
