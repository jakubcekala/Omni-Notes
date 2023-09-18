package it.feio.android.omninotes.espresso.data

data class TextNote(
    val title: String = "",
    val content: String = ""
)

data class ChecklistNote(
    val title: String = "",
    val points: List<String> = emptyList()
)

data class Category(
    val name: String = ""
)

object NoteDataProvider {
    val simpleTextNote = TextNote(
        title = "Title",
        content = "Example note content",
    )

    val simpleTextNoteUpdated = TextNote(
        title = "Updated title",
        content = "Updated note content",
    )

    val simpleTextNoteWithGroup = TextNote(
        title = "Note with group title",
        content = "Note with group content",
    )

    val simpleChecklistNote = ChecklistNote(
        title = "Title",
        points = listOf("Point 1", "Point 2", "Point 3")
    )

    val hashtagNote = TextNote(
        title = "Title",
        content = "Test hashtag\n#test"
    )

    val category = Category(
        name = "Cat1"
    )
}