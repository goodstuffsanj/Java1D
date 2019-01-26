# SUTD Booking App

As SUTD Students, we are given too many portals to go through in order to book any facilities in the school. Be it booking meeting rooms, consultations with the faculty, Fab-Lab facilities, or other kinds of facilities, we are always redirected to different portals to book them and sometimes due to internal system errors we may even fail in booking said facilities. This causes some under-utilisations in terms of facilities because it is such a pain to go through all these portals to book 1 hour of the venue. 

As such, there have been many complaints raised by the student population regarding this as it is both inconvenient and confusing to have so many different portals for booking. In view of this, our group has decided to tackle the the issue by creating a  One-Stop SUTD App to solve these redirection issues so that students will be able to spend less effort on the booking process and instead be able to enjoy being able to book the required school resources with ease.

Our app will mainly focus on two important aspects of the booking issue, the first being the booking of time slots with the professors, and the second being the booking of facilities in the school. We decided to focus on these two aspects as they are vital features for students (and professors) but yet remain problematic and inefficient. For booking of consultations with professors, it is sometimes difficult to find the times, outside of their office hours, at which they are free to discuss and consult about the projects. Even if the students manage to find the time, the professors may not always be able to reply in time for the students to see, which results in clashes in schedule. Our group feels that it is undeniably ineffective and inefficient for this to continue and hence hope that with the addition of our app, the SUTD community will have an easier time coordinating meetings with the faculty members.

The other focus of our app is the booking of available facilities in school. We have identified this process as similar to the process of booking time slots with the professors, with the only difference being the fact that most bookings do not need to undergo review before being accepted (i.e. can be accepted immediately upon request). Similarly, the reason why we are focusing on this aspect is because of the ineffective and inefficient process by which it is done. However, on top lack of efficiency of the booking process, we have also focused on this problem in order to prevent the underutilisation of the facilities in school due to the overwhelming number of portals that the students have to go through. We aim to mitigate the tediousness of the booking process and create a more pleasant booking process for the SUTD population.


## UI

<p align='center'>
  <img src='images/1.png' width=200px/>
  <img src='images/2.png' width=200px/>
  <img src='images/3.png' width=200px/>
  <img src='images/4.png' width=200px/>
  <img src='images/5.png' width=200px/>
  <img src='images/6.png' width=200px/>
  <img src='images/7.png' width=200px/>
</p>

## Features

Students:
- Booking People: professors, staffs, CDC staffs
- Booking Facilities:  meeting rooms, laser machine, fablab, etc

In student version, students can book school facilities and send request for consult with prof or other staff within one week. 


Professors:
- Requests: student meeting requests
- Booking facilities: meeting rooms, laser machine, fablab, etc

In prof version, prof also can book the school facilities like students. Instead of sending requests, profs can check the requests that they have received and accept or decline the requests flexibly.

## App Flow

<p align='center'>
  <img src='images/app_flow.png' width=600px/>
</p>

## Technology

1. [DynamoDB][1]

## Open Source
1. [Glide][2]
2. [MaterialCalendarView][3]

## Team
- Ivan Christian
- James Andrew Pohadi
- Gou Yuanyuan
- Rachel Chua
- Shanjay Shankar
- Wang Zijia

[1]: https://aws.amazon.com/dynamodb/
[2]: https://github.com/bumptech/glide
[3]: https://github.com/Applandeo/Material-Calendar-View
