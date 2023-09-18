Feature: Notes viewing
  Scenario: Add a new group
    Given user has few notes existing in the app
    And notes are not in any of group
    When user selects a note from the list
    Then the note details screen is visible
    When user click on the Group button in the toolbar
    Then "Categorize as" dialog is visible
    And Add category button is visible
    When user clicks on the Add category button
    Then "Edit category" dialog is visible
    When user enters a title and selects color of category
    And user is clicks on the OK button
    Then user is back in the details screen
    And "Category saved" massage is visible on the top of the screen
    And there is selected color marker next to the Title field
    When user goes back to Main screen by clicking on the back button
    Then there is a color marker next to the item

  Scenario: Remove an existing group
    Given user has a note which is in the any group
    When user selects a note from the list
    Then the note details screen is visible
    When user click on the Group button in the toolbar
    Then "Categorize as" dialog is visible
    And selected group is visible under the title
    And Remove category button is visible
    When user clicks on the Remove category button
    Then user is back in the details screen
    And there is no color marker next to the Title field
    When user goes back to Main screen by clicking on the back button
    Then there is no color marker next to the item

  Scenario: View the group management dialog
    Given user has a note which is in the any group
    When user selects a note from the list
    Then the note details screen is visible
    When user click on the Group button in the toolbar
    Then "Categorize as" dialog is visible
    And selected group is visible under the title
    And there is number of items in the group visible
    And Remove cateogry button is visible
    And Add cateogry button is visible

  Scenario: Edit an existing note content and add a new tag to the note
    Given has at least one note present in the app
    When user goes to details screen of the note
    And user changes a title
    And user changes a Content
    And add a single hashtag to the content of the note
    Then user saves note by clicking on the back button
    When user opens back the note
    Then all updated data is visible in the details screen
    When user clicks on the Add tags button in the toolbar
    Then "Select tag(s)" dialog is visible
    And added hashtag is visible on the list
    And added hashtag is selected
    When user clicks on the "OK" button in the dialog
    Then user is back to the details screen

  Scenario: Edit an existing note content
    Given user has at least one note available on the list
    When user select the note from the list
    And user changes a title
    And user changes a Content
    And user changes reminder date
    Then all updated data is visible in the details screen
    When user goes back to Main screen by clicking on the back button
    Then updated note is visible
    And all update date is visible in the note list

  Scenario: View an existing note
    Given user has at least one note available on the list
    When user select the note from the list
    Then Note details screen is visible
    And Attachment, Category, Add tags, Share icons are visible in the toolbar
    And the Title field has correct data
    And the Content field has correct data
    And the Reminder field has correct data

