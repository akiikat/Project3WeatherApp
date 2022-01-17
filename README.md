<p>Project Details:&nbsp;</p>
<p>Design a basic weather app that displays both Current Weather and 7-day forecast per the &ldquo;city name&rdquo; as user input. Written in Kotlin using Android Studio IDE.&nbsp;</p>
<p><br></p>
<p>Architecture:</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>MVVM Architecture</p>
<p><br></p>
<p>Libraries and Tools:</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>Volley</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>DataBinding</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>LiveData</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>Picasso</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>ViewModel</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>Web Service: OpenWeatherMap API</p>
<p><br></p>
<p>Design</p>
<p>I like the clean &lsquo;at a glance&rsquo; view:</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>Main Activity layout consists of plaintext for Searching the city name, a button for the API call and a Fragment Container for the weather data.&nbsp;</p>
<p>&bull;<span style="white-space:pre;">&nbsp; &nbsp;&nbsp;</span>In the main fragment a Linear Layout holds the &ldquo;current weather&rdquo; data. A recycler View inflated to a card layout populates the container with the &ldquo;7-day forecast&rdquo; data&nbsp;</p>
