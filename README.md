# Greventure

Greventure is an Android application designed to connect users with their local environments through a map-based social media platform. Developed by the 7SHEESH team during Raion HackJam 2024, Greventure aims to foster sustainable urban communities by providing information about local activities, events, and important locations, particularly those related to city sustainability and environmental care.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Team](#team)
- [Acknowledgments](#acknowledgments)

## Introduction

Greventure aligns with Sustainable Development Goal (SDG) 11: "Sustainable Cities and Communities." The app seeks to:
- Increase local engagement by encouraging participation in community activities.
- Promote city sustainability by providing information on eco-friendly practices and locations.
- Strengthen the connection between users and their cities.
- Enhance community ties by fostering relationships among local residents.

## Features

- **Maps & Bubbles:** Interactive maps using Google Maps API, with bubbles that display information about local events, activities, or places.
- **Threads:** A discussion feature allowing users to interact within bubbles.
- **Local News:** A news section providing users with the latest updates and events relevant to their community.

## Tech Stack

- **Kotlin:** The primary programming language for Android development.
- **Jetpack Compose:** For building the app’s UI declaratively.
- **Supabase:** For backend services including authentication and data management.
- **Google Maps API:** For providing interactive map features.

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ahargunyllib/Greventure.git
   cd greventure
   ```
2. **Open the project in Android Studio:**
   - Launch Android Studio and select "Open an Existing Project."
   - Navigate to the `greventure` directory and open it.

3. **Configure API Keys:**
   - Obtain a Google Maps API key from the [Google Cloud Console](https://console.cloud.google.com/).
   - Add the API key to your `local.properties` file:
     
     ```
     MAPS_API_KEY=your_google_maps_api_key
     ```

4. **Sync Gradle and Build the Project:**
   - Click "Sync Project with Gradle Files" in Android Studio.
   - Once the sync is complete, build and run the project on an emulator or physical device.

## Usage

Once the application is installed, users can:
- Explore local activities and places using the interactive map.
- Join discussions and connect with other users through the threads feature.
- Stay updated with local news relevant to their community.

## License

Greventure is released under the MIT License. See [LICENSE](LICENSE) for more information.

## Team

- **Product Manager:** Putra Cakrawala Aulia Syafiq.
- **UI/UX Designer:** Afiif Al Hauzaan A.
- **Programmer:** Elgin Brian Wahyu Bramadhika.
- **Programmer:** Nugraha Billy Viandy.

## Acknowledgments

Greventure was developed during Raion HackJam 2024. We would like to thank all the participants and organizers for their support and contributions.
