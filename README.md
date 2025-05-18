![License: MIT](https://img.shields.io/badge/License-MIT-green)
![University: IUT Montreuil](https://img.shields.io/badge/University-IUT%20Montreuil-red)
![machine: learning](https://img.shields.io/badge/big-data-blue)
![java: 17](https://img.shields.io/badge/java-8-brightgreen)
![Contributors](https://img.shields.io/badge/contributor-1-orange)
![Stars](https://img.shields.io/github/stars/Fab16BSB/SearchEngine?color=orange)
![Fork](https://img.shields.io/github/forks/Fab16BSB/SearchEngine?color=orange)
![Watchers](https://img.shields.io/github/watchers/Fab16BSB/SearchEngine?color=orange)

# Search Engine Project

## 🌍 Multilingual README Versions

- 🇫🇷 [French](./README.fr.md)
- 🇬🇧 [English (you are here)](#)
- 🇪🇸 [Spanish](./README.es.md)

---

## 📘 Project Overview

This project was carried out during my 2nd year of the DUT program at **IUT de Montreuil**. It implements three different search engines over a corpus of **138 English hotel reviews from Chicago**.

The goal is to compare and understand how search engines work based on:

- **Boolean search**,
- **Vectorial search** (TF-IDF + cosine similarity),
- **Probabilistic search**.
---

## 📊 Dataset

The dataset consists of 138 files, each containing a real English hotel review, automatically scraped from hotel review websites. Due to this method of collection, the texts may contain formatting errors, informal expressions, or noise typical of uncleaned data. These customer reviews are used to evaluate the relevance and performance of the implemented search engines.

---

## ⚙️ General Operation of the Engines

### 1. Boolean Engine

The Boolean engine relies on the exact presence or absence of terms in the documents. It uses logical operators `AND`, `OR`, `NOT` to combine search criteria.  
**Advantages:** simple, fast.  
**Limitations:** does not account for word frequency or relative importance.

### 2. Vectorial Engine (TF-IDF + Cosine Similarity)

This engine computes a vector for each document and for the query, weighted using the TF-IDF (Term Frequency – Inverse Document Frequency) measure.  
It then calculates the cosine similarity between these vectors to assess document relevance.  
**Advantages:** considers the relative importance of words in the corpus.  
**Limitations:** sensitive to document length.

### 3. Probabilistic Engine (Simplified Frequency-Based Model)

This engine applies a **simplified binary probabilistic model**.  
For each query term, it estimates its **probability of occurrence in the documents** and computes a **log-linear weight**.  
Documents containing query terms are then compared using **cosine similarity** between weight vectors.

**Advantages:** Intuitive probabilistic approach, weights words based on their distribution in the corpus.  
**Limitations:** Does not consider document length and does not model term saturation.

---

## 🧑‍💻 Technologies Used

- **Language:** Java 8
- **Tools:** JDK 8+, any Java IDE

---

### 📁 Project Structure

- `hotels/`: folder containing the dataset files (the reviews)
- `src/`: folder containing the Java source files

---

### 💻 Install Java (If Java is not installed)

If you do not have Java installed, you can follow one of my YouTube videos to install Java on various platforms:

- **Linux**: [Install Java on Linux](https://www.youtube.com/watch?v=-9G2YARJ0jM)
- **Mac**: [Install Java on Mac](https://www.youtube.com/watch?v=hts1lGSKZfc&t=1s)
- **Windows**: [Install Java on Windows](https://www.youtube.com/watch?v=vCQHCYM_OVY)

---

### 📝 Compilation and Execution

1. **Clone the repository**

   ```bash
   git clone https://github.com/Fab16BSB/SearchEngine
   cd src
    ```
2. **Compile**

   ```bash
   javac *.java
   ```
   
3. **Run**

    ```bash
    java Main
    ````
---
### 📈 Results

#### Boolean Engine
```
Fichiers déjà présents, on passe au chargement.
Loading Documents from "documents.data" …
Done: Documents (18231 entries)
Loading Vocabulary from "vocabulary.data" …
Done: Vocabulary (115587 entries)

Quel type de recherche souhaitez-vous utiliser ?
1) Boolean   2) Vectorielle   3) Probabiliste
Votre choix (1-3) : 1
Moteur booléen : utilisez les opérateurs AND, OR, NOT pour formuler vos requêtes.

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
Clean AND spacious rooms with friendly staff.
Aucun résultat trouvé.

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
Noisy OR crowded location, but good value.

Oct 27 2004 - Can you say DAYS INN!!!
It's still a Days Inn with paper thin walls and noisy elevator. Stay away, Stay far, 
far away from this so called hip hotel. May be great for juvenile college type. I don't understand 
all the hype of this hotel. It's not worth the money. I found a MUCH nicer hotel in the area at 
about 1/2 the price, but I'm keeping that secret to myself!

Dec 21 2006  - Ridiculously outstanding!
Was stuck in Chicago for a few days due to a huge storm in Denver - booked my room through 
Starwood Preferred Guest and from the moment I entered the lobby, I knew I was in for an experience. 
Exceptional customer service by all staff. The small touches are what I noticed at the W ... a real 
person instead of a recording for the wake up call ... staff noting their names and indicating that 
I could call them personally for any needs ... an attentive concierge with great suggestions ... 
turndown service on request .... Just wonderful. Hip, trendy lobby scene - rooftop bar is a little 
crowded and small, but the views from 33rd floor were likely stunning. Since I was traveling alone, 
I far preferred the &quot;Living Room&quot; lobby bar. Less clubby. Better people watching. My 23rd 
floor lakeview room featured a luxurious shower - a deluge from above! A perfect weekend that could 
have been spent at a regular, boring, chain turned into an adventure to remember. Absolutely, stay 
at the W Lakeshore.

2 résultat(s) trouvé(s)

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: quit
Au revoir !
```

#### Vectorial Engine
```Fichiers déjà présents, on passe au chargement.
Loading Documents from "documents.data" …
Done: Documents (18231 entries)
Loading Vocabulary from "vocabulary.data" …
Done: Vocabulary (115587 entries)

Quel type de recherche souhaitez-vous utiliser ?
1) Boolean   2) Vectorielle   3) Probabiliste
Votre choix (1-3) : 2

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
Excellent value for the price with a great downtown location.

Feb 18 2005 - W='wanna be important' but get used to hearing 'no' stayed w/ my family on valantines 
weekend.Room was cramped and very dusty - all 4 of us had problems from the dust when we woke up 
the next morning.Only one restaraunt - only serving a 9 course meal - ONLY -- they told us to take 
our kid to the BAR! for dinner!Who are they kidding!Called room service - asked for a hamburger - they 
said &quot;can't do that&quot; asked for Mac/Cheese for kids - they said &quot;can't do that 
either&quot;we ordered a steak to split and a chicken ceasar salad --&gt; it cost $73 !! - no drinks 
we ordered either! What a RIP! food was fair -- for that price it should have been outstanding.for the 
level of hotel and cost they should have bent over backwards.Went to breakfast the next morning w/ 
continental free breakfast coupons - we have to make sure our son has protein and special food due to 
food allergies. They told us we couldn't apply a dollar value to the coupon to get him a different meal 
-- we just continually heard &quot;NO&quot; to everything.Strangely the following night we went to the 
Ritz Carlton for dinner - and they bent over backwards for us.. taking our coats, showing to the 
restrooms, table, etc, extremely polite, clean beautiful place.. worth the money... but NOT at the W 
Lakeside!Huge disappointmentThey even wanted US to call HQ to make sure my husband's unused hotel 
points were redeposited into his account after we decided to switch hotels. They made NO attempt to 
make things right or do what any other hotel would do.

Jun 27 2005 - You can't beat the location
We just returned home from the W Lakeshore Chicago and were pleased with the hotel. My wife, 
two children (6 and 3) and I stayed there for 2 nights and would stay again if we ever traveled back. 
Although the hotel appears to cater to a different crowd (singles and couples without kids) the staff 
was very helpful with all requests and even gave both of our children a pack with a bunch of games, 
a yoyo and water bottle upon our arrival. One cautionary note: we ran into a hotel employee on our 
elevator ride down to go walking and he offered us some water to take with. He then asked us for our 
room number so that he could send up �something for the boys.� We thought this was a very nice 
gesture but found out at checkout that he had charged the water to our room even though we never asked 
for it. The best thing about the hotel is the location. It is only a short walk of 2 blocks to Navy 
Pier, 10- 15 minute walk to Michigan Avenue and 25 minute walk to Millennium Park. We took a water 
taxi from Navy Pier to the Shedd Aquarium and Field Museum to save walking in the 96 degree heat for 
$12 round trip per adult and $6 for children over 3; this is much less expensive then a regular taxi 
and a lot cooler. There is also a FREE trolley system that goes all over the city with connections at 
Navy Pier. The only problem is that you may have to get on and off a couple of different trolleys to 
get to your destination, but it is FREE. The beds were VERY comfortable, the claim on their website 
about �pillow-top mattress, 250 thread-count linens and fluffy goose down pillows� was true. For a 4 
star hotel the room was a little on the small side, it fit 2 double beds with just enough space for a 
chair by the window. The only thing that I didn�t care for about the room was the fold out window 
shutters in the wall between the bedroom and bathroom; they open and close so that you can watch your 
spouse shower. However, this could be uncomfortable if you have other people like older children 
staying in your room. Plus the shutters don�t give you a feeling of privacy. The swimming pool was 
adequate in size but there was NO hot tub. My wife went up to the bar on the 33rd floor one night 
while I was tucking in the boys and told me that it isn�t very large or impressive, but then we 
weren�t there for the nightlife. Be prepared to pay for parking if you bring a car, the rate was $39 
per night. Also, an Internet connection in the room if needed was another $15 per day. We received a 
VERY good rate through Hotwire and felt like we hit the jackpot. It is definitely a 4 star hotel 
and again we liked staying there.

Jul 29 2004 - Dissapointed
I am a travel agent that lives about 30 miles outside of downtown Chicago and over the years I've had 
many opportunites to stay downtown at different hotels for various reasons. I chose this hotel because 
I'd heard many great things about the W properties from clients and friends, so I thought it would be 
perfect for 4 girls looking for a hip, trendy hotel for the night. My first impression upon entering 
the hotel was that it was very funky-fun and yet somehow tranquil in a minimalist sort of way. The staff 
and service I received upon check in, getting directions to a club and check-out were great and on par 
with how I expected to be treated at a hotel of this caliber. Where my disspointment comes from with this 
property is in the rooms themselves. The rooms are very small - there isn't much room to maneuver between 
the end of the beds and the counter that holds the tv, mini bar etc. The lighting is awful - in the bathroom 
and in the bedroom. Trying to put on makeup for the evening was a complete joke and we all left asking each 
other if we had too much on because we couldn't see what we were doing. The color scheme in the room doesn't 
make this any easier either, since everything is done up in dark browns, black cherry, black etc. The ceiling 
itself is even painted dark brown, which certainly doesn't help reflect light. The one positive thing that I 
can say about the room is that the beds are as wonderful as they say they are. Amazingily soft and luxurious 
- it would have been probably the best night of sleep in a hotel bed that I'd ever had except for the fact 
that there was only a bamboo woven mat shade covering the windows that did absolutely nothing in the way of
blocking out any morning light. Needless to say after getting to bed at 5'am weren't happy about being woken 
up by bright sunshine at 830'am. I would not recommend this hotel to people with kids, anyone looking for a 
spacious and well-lit room, or for late night people unless you plan on bringing a sleep mask to block out 
the sunlight. For an overall more comfortable, pleasant stay in downtown Chicago I highly recommend the Hyatt
Regency, Swissotel, Sheraton, Embassy Suites, or Hilton.

Feb 7 2004 - Love those W beds
What a great hotel!! The beds are wonderful, you might not want to get up in the morning. All the staff I 
encountered were friendly and helpful. The bar aka Living Room was a fun place to hang out with friends and 
have a drink or two. The food wasn't bad from the bar menu, didn't make it to the restaurant. Like some other 
reviewers said this really isn't the place to bring kids as it does have an adult atmosphere. Do yourself a 
favor and splurge for a lake view room. You can take a shower while looking out over the lake. The hotel is 
only a short cab ride away from anything in the city. Like most W's the halls are a bit dark as are the rooms 
but it just helps to add to the atmosphere of the place. I love all the W hotels I've stayed at but I think 
this one is my favorite. The color scheme in the rooms are relaxing and romantic, lots of black, tan and red. 
I'll be back.

Nov 21 2003 - Nice Place
The W Lakeshore is a wonderful hotel, though certainly developed for a certain crowd. This, as are most W hotels, 
has 20-35 year-old, affluent people in mind as its clientele. If you prefer more traditional luxury like the Four 
Seasons or Ritz-Carlton, or are traveling with a family, do yourself a favor and do not stay here. You will not be 
happy.My girlfriend and I stayed here one night to relax, we go to school in Chicago, and both of us loved the hotel. 
To feel comfortable in the public areas of this hotel, dress well. It is a very chic styled hotel, though not cheap 
or cheezy. The lobby turns into a night club atmosphere at night.The service we received was as good as I have ever 
experienced at a Park Hyatt or Four Seasons. We checked in at about 7 PM on a Saturday evening. The SPG line was 
quick and we were upgraded without question. At breakfast the next morning in the Wave restaurant, we had free 
continental breakfast, but our waiter gave us the entire menu for free.The rooms, undoubtedly are small, as the
hotel is a converted Days Inn. Nonetheless, they are wondefully furnished. The bed, as in most Starwood hotels, 
is second to none. The hotel certainly has the best view of any hotel in Chicago. The bathrooms are very cool. 
There is an Asian, wood-paneled, sliding door and nothing surrounding the shower (makes for a very nice morning 
shower with the girlfriend ;) ).Check-out was a breeze. So, all in all, I thought it was a very cool hotel with 
excellent service. Though, again, I caution you in choosing this hotel. The rooms are far too small for a family. 
The bathrooms are not cool unless you're traveling with someone you feel comfortable being naked around. Also, 
the night atmosphere is certainly not for small children or adults who prefer a more laid-back atmosphere. 
But, if you like a hip place and to hang out with the in-crowd, chances are you will enjoy this hotel.

5 résultat(s) trouvé(s)


Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
The breakfast buffet offered many tasty options

Jun 3 2004 - Excellent location but nice and quiet inside!
Stayed over the weekend, and LOVED the location/ proximity to great Michigan Avenue shopping. Also two blocks 
from Lawry's Steak House (excellent also). The rooms were quiet and the food at the restaurant was good, although 
the breakfast buffet was $16 per person (not sure that was a great value but Dunkin Donuts is a block away). 
Only negative was small pool/workout area and slow valet service to retrieve car. Otherwise, a great location 
and very nice property. Walking distance to Art Institute, Navy Pier, Aquarium, etc. 

Aug 20 2007  - A Chicago Staple
Though this hotel seems to get mixed reviews, I find staying there a consistently good experience and prefer 
it over the other W (on Adams I believe) and Westins in the area. The front desk staff has always been polite 
and very service-oriented. They always try to upgrade me to a lake-view room (great view!). Room service was 
tasty but quite slow the last time I stayed there, but they comped my meal without my even having to make a 
comment about it. The rooms aren't huge, but they are well-appointed. As all Ws do, they have great Bliss 
products for the bath. For other business travelers, I have never had issues getting online from the rooms, 
which is a bit rare in my experience. I have had FedExes sent to me at the hotel, and they are very 
conscientious about prompt notification of receipt. Note that the HVAC is a bit disruptive, as it's unit near 
the window that turns on and off quite abruptly, so if that type of thing keeps you up at night, you might 
want to bring earplugs. Further, I had Room #2002 the last time, and it was right next to a set of elevators, 
so note that you can definitely hear the &quot;swooshing&quot; up the up/down movement all night long in the 
rooms that end in 02 (not sure what the room # was next to the other set of elevators).

Jan 15 2007  - OK Stay at the W
I stayed at the W Lakeshore for one night on January 13th. I would agree with the previous post that had both
 pros and cons. The views are indeed fabulous. I loved that I could work out in the healthclub overlooking the
lake. The hotel is totally hip and trendy, although I will say that I was not overly impressed with the Whiskey
Sky bar. I personally like the lobby bar much better. Also, the hotel smells fabulous and the location is great.
On the downside, I thought the walls were a little thin. You could hear everyone open and close their doors 
all down the hallway. I also didn't really care for the shutter window and shuttered door concept on the bathroom. 
It leaves much to be desired for privacy. The only other thing worth mentioning, which has also been mentioned 
before, is that they do nickel and dime you to death. I'm used to mini bar prices being high, but $6 for a small 
bottle of water is ridiculous. Breakfast for two a la carte in the restaurant ( not room service ) was $40 with 
the tip. I will say though, due to the great location there are plenty of nearby dining options outside the hotel 
that won't break you. Everyone that I dealt with on the staff was great and if I could get a decent rate I would 
stay at this hotel again.

Nov 22 2004 - Great hotel and staff
My family and I just got back from this hotel and I did not want to leave! I have stayed at many other &quot;W&quot;
but I have to say that the staff here was wonderful. From the moment that I arrived it was geat. Dainel check us in
(early!) and even offered a free upgrade. He gave us a nice lakeview room (awsome view!) for free. The bellman Dave
Thompson was also very nice. He call us by name, and when we went to get a cab, he offered us a complentary ride to
our destination in a 2004 Chrysler, water and snaks provide!! When it was time to leave they also give us a late
check-out with out any problems. I travel a lot, and this by far was the best hotel staff I've ever delt with. And 
last but not least, that the rooms are very nice too.

Nov 21 2003 - Nice Place
The W Lakeshore is a wonderful hotel, though certainly developed for a certain crowd. This, as are most W hotels, 
has 20-35 year-old, affluent people in mind as its clientele. If you prefer more traditional luxury like the Four 
Seasons or Ritz-Carlton, or are traveling with a family, do yourself a favor and do not stay here. You will not 
be happy.My girlfriend and I stayed here one night to relax, we go to school in Chicago, and both of us loved the 
hotel. To feel comfortable in the public areas of this hotel, dress well. It is a very chic styled hotel, though 
not cheap or cheezy. The lobby turns into a night club atmosphere at night.The service we received was as good as 
I have ever experienced at a Park Hyatt or Four Seasons. We checked in at about 7 PM on a Saturday evening. The 
SPG line was quick and we were upgraded without question. At breakfast the next morning in the Wave restaurant, 
we had free continental breakfast, but our waiter gave us the entire menu for free.The rooms, undoubtedly are 
small, as the hotel is a converted Days Inn. Nonetheless, they are wondefully furnished. The bed, as in most 
Starwood hotels, is second to none. The hotel certainly has the best view of any hotel in Chicago. The bathrooms
are very cool. There is an Asian, wood-paneled, sliding door and nothing surrounding the shower (makes for a 
very nice morning shower with the girlfriend ;) ).Check-out was a breeze. So, all in all, I thought it was a 
very cool hotel with excellent service. Though, again, I caution you in choosing this hotel. The rooms are far
too small for a family. The bathrooms are not cool unless you're traveling with someone you feel comfortable 
being naked around. Also, the night atmosphere is certainly not for small children or adults who prefer a more 
laid-back atmosphere. But, if you like a hip place and to hang out with the in-crowd, chances are you will enjoy 
this hotel.

Jul 29 2004 - Dissapointed
I am a travel agent that lives about 30 miles outside of downtown Chicago and over the years I've had many 
opportunites to stay downtown at different hotels for various reasons. I chose this hotel because I'd heard 
many great things about the W properties from clients and friends, so I thought it would be perfect for 4 
girls looking for a hip, trendy hotel for the night. My first impression upon entering the hotel was that 
it was very funky-fun and yet somehow tranquil in a minimalist sort of way. The staff and service I received
upon check in, getting directions to a club and check-out were great and on par with how I expected to be 
treated at a hotel of this caliber. Where my disspointment comes from with this property is in the rooms 
themselves. The rooms are very small - there isn't much room to maneuver between the end of the beds and 
the counter that holds the tv, mini bar etc. The lighting is awful - in the bathroom and in the bedroom. 
Trying to put on makeup for the evening was a complete joke and we all left asking each other if we had too
much on because we couldn't see what we were doing. The color scheme in the room doesn't make this any 
easier either, since everything is done up in dark browns, black cherry, black etc. The ceiling itself is
even painted dark brown, which certainly doesn't help reflect light. The one positive thing that I can say 
about the room is that the beds are as wonderful as they say they are. Amazingily soft and luxurious - it 
would have been probably the best night of sleep in a hotel bed that I'd ever had except for the fact that
there was only a bamboo woven mat shade covering the windows that did absolutely nothing in the way of 
blocking out any morning light. Needless to say after getting to bed at 5'am weren't happy about being 
woken up by bright sunshine at 830'am. I would not recommend this hotel to people with kids, anyone 
looking for a spacious and well-lit room, or for late night people unless you plan on bringing a sleep 
mask to block out the sunlight. For an overall more comfortable, pleasant stay in downtown Chicago I 
highly recommend the Hyatt Regency, Swissotel, Sheraton, Embassy Suites, or Hilton.

6 résultat(s) trouvé(s)

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: quit
Au revoir !
```

#### Probabilist Engine
````
Fichiers déjà présents, on passe au chargement.
Loading Documents from "documents.data" …
Done: Documents (18231 entries)
Loading Vocabulary from "vocabulary.data" …
Done: Vocabulary (115587 entries)

Quel type de recherche souhaitez-vous utiliser ?
1) Boolean   2) Vectorielle   3) Probabiliste
Votre choix (1-3) : 3

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
The room was clean and spacious, perfect for a weekend stay.

Sep 21 2009  - Slum
Not only are the rooms jacked up but the Manager of the Building is total lacking customer service skills 
and has no respect for heterosexual people obviously. I had never met this guy before and told him about 
my shower not working in my room in which I paid for a private room with shower and he acted like a kid, 
stating &quot;you can leave when your rent is up&quot;. My rent is up on Tuesday, at noon, and I definitely
would be out of the building. If Abbott wants its hotel to improve, it first must rid of the manager. 
And the next time a paying customer states they shower is not working the Manager should just say I 
will have maintenance take a look at the situation and we will have an answer for you when you get back. 
Belmont is a historical place and ABBOTT HOTEL must shape itself around the Beauty Belmont holds instead of 
having a Bum Manager run and ruin ABBOTTS Hotel. GET RID OF THE MANAGER! Once you do that, I think the place 
will get better ratings.

1 résultat(s) trouvé(s)

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: 
Staff were friendly but the location was a bit noisy at night.

Aug 16 2005 - The Abbott has improved a LOT!
I stayed at the Abbott this month, it was not my first time there, but I was very glad to see that they had
improved a lot: new beds and carpets, a comfortable lobby, and no roaches any more! (I saw only one, in a 
dustbin). The location is superb, close to 24-hour restaurants and stores, as well as a subway station and 
plenty of buses! Chicago is a rather expensive city, so it is a real pleasure to find this little gem, with 
friendly neighbours and very friendly and professional staff. (Also, the air conditionning is great, 
particularly during these hot summer months).

1 résultat(s) trouvé(s)

Tapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: quit
Au revoir !
````

---

### 🙌 Acknowledgements
I would like to thank my instructor for offering me this project and for his valuable advice.
