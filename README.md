# GitDiffTextView #

With this android library you are able to display DIFF in a TextView.

Usage 
========

Download library and copy into your project, then add gradle dependency:

``` groovy
    compile project(':libraries:GitDiffTextView')
```

Add GitDiffTextView to your layout: 

``` xml

<com.alorma.diff.lib.DiffTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/diffTextView"/>

```

Add diff text:

´´´ java

DiffTextView diffTextView = (DiffTextView) findViewById(R.id.diffTextView);

String diffText = "@@ -29,6 +29,7 @@\n                 android:resource=\"@xml/searchable_repos\" />\n         </activity>\n         <activity android:name=\"com.alorma.github.ui.activity.ProfileActivity\" />\n+        <activity android:name=\"com.alorma.github.ui.activity.CommitDetailActivity\" />\n         <activity android:name=\"com.alorma.github.ui.activity.RepoDetailActivity\" />\n         <activity android:name=\"com.alorma.github.ui.dialog.NewIssueCommentDialog\" />\n-         <activity android:name=\"com.alorma.github.ui.activity.FileActivity\" />";

diffTextView.setMaxLines(5);

diffTextView.setText(diffText);
´´´