A Java application for tracking and managing football match scores during the World Cup. This scoreboard system allows real-time tracking of matches, score updates, and provides sorted summaries based on match status and total score.

HOW TO CLONE THE REPOSITORY:
git clone https://github.com/KristianPejic/SportRadar.git
cd world-cup-scoreboard

Features:

Live Match Tracking: Start, update, and finish football matches
Real-time Score Management: Update scores for ongoing matches
Intelligent Sorting: View summary of matches sorted by total score and start time
Robust Validation: Input validation ensures data integrity
Exception Handling: Comprehensive error management

Installation Prerequisites:

-Java 17 or higher
-Maven 3.6+

🏗️ Architecture
The project follows a clean, modular architecture:

![image](https://github.com/user-attachments/assets/326e543a-0d45-4262-80f9-8e04ba24970b)

Key Components:

Match: Core model representing a football match with teams and scores
WorldCupScoreboardService: Main service implementing the scoreboard functionality
Validators: Ensure data integrity and valid operations
Custom Exceptions: Provide meaningful error handling
WorldCupScoreboardServiceTest: For testing each method,validation,exception
