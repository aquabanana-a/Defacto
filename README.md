<table>
  <tr>
    <td><img src="https://github.com/aquabanana-a/Defacto/raw/459c0b19ce12c146ef4020abddde25afc2d93eb1/Screenshot_2025-07-01-13-44-53-352_com.banana.defacto.jpg" width="350"/></td>
    <td><img src="https://github.com/aquabanana-a/Defacto/raw/459c0b19ce12c146ef4020abddde25afc2d93eb1/Screenshot_2025-07-01-13-45-05-693_com.banana.defacto.jpg" width="350"/></td>
  </tr>
</table>

Test task

You need to develop an app on Java/Kotlin for Android, “Interesting facts about numbers”. The application should allow the user to enter the number about which he wants to know an interesting fact, as well as - to randomly generate a number and get a fact about it. Application must contain 2 screens.

Main screen
It should be divided into two parts, the upper part is a field for entering a number, the button "Get fact" and the button "Get fact about random number", the lower part - display the history of user requests, each history element should show the number the user searched for and fact preview (everything that fits in one line), clicking on the element should open the 2nd screen.

2nd screen
Must display to the user the number and full text of the fact about the previously selected number. It should also be possible to return to the previous screen.

Task description
To get information about the number - use api http://numbersapi.com
For example, for the number "10" - the query http://numbersapi.com/10
To get the fact about the random number - http://numbersapi.com/random/math
For “http” queries don’t forget to add android:usesCleartextTraffic="true" to your Manifest file.

Main requirements
programming language: Java/Kotlin, IDE - Android Studio;
Queries to api must occur asynchronously (eg Coroutine, Rx);
Use Room to store data (fact search history);
The UI of the application is free, it is not a criterion for evaluating a test task;
