# ContactApp with National ID Processing

This is a Kotlin-based Android app built with Jetpack Compose. The app accepts a National ID as input, validates it, and extracts information such as birth year, month, day, governorate of birth, and gender.

## Features:
1. **OutlinedTextField for National ID Input**: The user can input their 14-digit National ID, which is validated for correctness.
2. **Real-Time Validation**: Error messages appear if the input is blank or does not meet the required 14 digits.
3. **Birth Year, Month, and Day Extraction**: Based on the National ID, the app identifies and displays the birth year (19xx or 20xx), month, and day.
4. **Governorate of Birth Identification**: The app extracts and displays the governorate of birth (e.g., Cairo, Fayoum) based on the National ID.
5. **Gender Identification**: Gender is determined from the National ID, displaying either "Man" or "Woman."
6. **Snackbar and User Feedback**: Upon submitting the ID, relevant information is displayed on the screen.
7. **Manage Status Bar**: Customizes the status bar color and icon behavior for a modern UI look.

## How It Works:
- The app utilizes an `OutlinedTextField` for inputting the National ID.
- Upon clicking the "Add ID" button, the app extracts information from the ID string:
   - The first digit of the ID is used to determine if the birth year is from the 1900s or 2000s.
   - The next digits are used to extract the month and day.
   - The app matches certain index values to display the governorate of birth.
   - Finally, it checks the gender using specific digits of the ID.
   
## Code Breakdown:

1. **MainActivity**: Initializes the app and sets up the edge-to-edge display.
2. **ContactApp**: Main UI component that handles the input, validation, and extraction of information from the National ID.
3. **ManageStatusBar**: Customizes the status bar for a polished user interface.
4. **NationalId Data Class**: Holds the National ID string for processing.

## Libraries and Dependencies:
- **Jetpack Compose**: For modern UI design with declarative components.
- **Material 3**: For styling and theming the app.
- **WindowCompat**: For handling the system window and enabling full-screen mode.
