   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5      ,    &FacultyManager.Components.PopupMessage   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      9local'clr-namespace:FacultyManager.Components     5        � 	Resources  5	          BooleanToVisibilityConverter��%   &	        �� 5
   
   6'   5      .��5      � .+�5      � y�6   �� *	Correct+ �� 	Converter# [   � RowDefinitions 5   
   �� 5      $��* �6   �� 5      $
��auto �6   �� 5      $��* �6   �� 5      $
��auto �6   .+�5   
   �� $��0��6   
��� � �6!   �� 5   
   $��1��6   
��� � �6!   �� 5   
   $��0��6    �� CornerRadius$ 50��6   $��60��5      $��60��5      $��10q�5      
�������$��3q�5      ._�5      �� $��40(�6   $
��Bold"�6&   
�������$��Center=�6K   $��Center=�6f   √5      5      �� 5   
   $��1��6   $��5q�6!   
������$��16(�6D   $��Registered Successfully��6R   $��Bottom=�6q   $��Center=�6�   6�   �� 5    
   $��2��6   $��20q�6!   $��Center=�6-   
���iii�$��
UltraLight"�6]   $��14(�6u   $
��Wrap=�6�   $w��qYou have successfully created a FacultyManager account,
 A verification code has been sent to your email account.��6�   6  �� 5!   
   $��3��6   $��5q�6   $��Continue��6)   $��40��6<   $	��140��6H   
������ $��0q�5"      
������ ��5!   T   �� *NavigateCommand+��5#      �� 5$       �� 
TargetType" ��6"   .R�5%      �� $ 10��6   $��40��6/   $	��140��6;   
��� � �$��4,4,4,4q�5&      
��� � �._�5'      �� #��z��6$   $��20(�6E   $��Center=�5(   $   $��Center=�6A   
�������5)   $   5*      5+      5-      5.      � 5/   
   y�6   �� *Error+# [   50      �� 51      $��* �6    �� 52      $
��auto �6    �� 53      $��* �6    �� 54      $
��auto �6    .+�56      �� $��0��6   
���  ��6%   �� 57      $��1��6   
���  ��6%   �� 58      $��0��6   $ 50��6"   $��60��59      $��60��5:      $��10q�5;      
�������$��3q�5=      ._�5>      �� $��40(�6   $
��Bold"�6*   
�������$��Center=�6O   $��Center=�6j   X5?      5@      �� 5A      $��1��6   $��5q�6%   
������$��16(�6H   $��registration failed��6V   $��Bottom=�6q   $��Center=�6�   6�   �� 5B      $��2��6   $��20q�6%   $��Center=�61   
���iii�$��
UltraLight"�6a   $��14(�6y   $
��Wrap=�6�   ��6�   �� *	Message+6�   �� 5C      $��3��6   $��5q�6"   $��	Try Again��6-   $��40��6A   $	��140��6M   
������ $��0q�5D      
������ ��5C   Y   �� *NavigateCommand+��5E      �� 5F      " ��6&   .R�5G      �� $ 10��6!   $��40��63   $	��140��6?   
���  ��$��4,4,4,4q�5H   !   
���  ��._�5I      �� #��z��6(   $��20(�6I   $��Center=�5J   (   $��Center=�6E   
�������5K   (   5L      5M      5O      5P      5Q      5R      