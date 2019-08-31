## Welcome to my Algolia Search Project

I chose the Bordeaux wine dataset to build my page around it using the Java back-end Algolia APIs. I created replica indices to allow sorting by price and quality of the wine. To make the page more aesthetically appealing, I added some images for logos and modified them a little to fit with the theme. You will notice that any wine with a quality rating of 95 or higher will have a little golden wine glass next to it to distinguish it from the other results.

I've also added the following filtering capabilities: 
- Range slider for year
- Range slider for price
- Filtering on red or white wine type
- Highlighting text matching search query
- Hover over enlarged bottle image
- Results statistics
- Pagination

I wanted to add further personalization by modifying the search bar to include a small wine glass in place of the default search button icon but had some trouble getting it to work. Another small issue I ran into were some descrepancies between the interactive tutorials for Algolia and the current implementation. For example, in Javascript, the "attributeName" is no longer a property and needs to be actually referred to as "attribute". But besides these small inconsistencies, the available documentation was fairly thorough.

# Question Responses

## Question 1
A record is an object which you want to search and can comprise of various properties. These records need to be indexed by collecting, parsing, and storing the data in order to quickly retrieve them when queried for.

## Question 2
Unfortunately, I did not use the dashboard for this assignment so I am not as familiar with it as with the back-end APIs that I used. But as an attempt to answer Matt's question, I would assume that the dashboard may be versioned and he can therefore, revert to an older version. I'm unfamiliar with what the dashboard used to look like, but in the current one, I can simply go to Indices -> "Pick index" -> Manage Index -> Delete.

## Question 3
No, it is not a lot of work. The process is highly tailored to your needs and skill level. You can use the provided dashboard UI to create an index for your data, add your data, and let Algolia's magic do the work. Alternitavely, you can connect to Algolia APIs on the back-end using a variety of supported programming languages. Algolia does all of the processing for you. After that, it's up to you how many rules and restrictions you want to add to your searches!
