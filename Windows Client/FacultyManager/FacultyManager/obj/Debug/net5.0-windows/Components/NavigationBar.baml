   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5      -    'FacultyManager.Components.NavigationBar   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      9local'clr-namespace:FacultyManager.Components     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      
������  � 	Resources  5       �� 
TargetType �� Property �� Value �� 
TargetType �� Triggers P� Property P� Value    BooleanToVisibilityConverter��%  '��       &	  �    �� 5   
   " ��6   .�5      ��  � ��
 ���� 6+   �� 5       � ��
 �����6+   �� 5       � ��$ 0q�60   �� 5       �@�Cursor$
 Hand��6'   �� 5       � �� 5      �� 5      " ��6&   .R�5      �� #��z��6$   5      5       5      P� 5       �@J�IsMouseOver$
 True��61   . �5      ��  � �� 5      �� 5      " ��6.   .R�5   "   �� #��z��6,   $��	Underline{�6M   5      5!      5"      5$      �� 5%   
   6'   5&      .��5'      � $
��1000��6   $��20 10q�6    � ColumnDefinitions 5(   
   �� 5)      $��* �6   �� 5*      $
��auto �6   �� 5+      $
��auto �6   �� 5,      $
��auto �6   �� 5-      $
��auto �6   �� 5.      $
��auto �6   �� 5/      $
��auto �6   �� 50      $
��auto �6   �� 51      $
��auto �6   .+�53   
   �� $��4��54      $��10 0 0 0q�55      $��
Add Course��57      ��56      �� *NavigateAddCourseCommand+y�58      �� *	AddCourse+	 �� 	Converter#	 [  �� 5:   
   $��5��5;      $��10 0 0 0q�5<      $
��Home��5>      ��5=      �� *NavigateHomeCommand+5>      �� 5@   
   $��6��5A      $��10 0 0 0q�5B      $��Login��5D      ��5C      �� *NavigateLoginCommand+y�5E      �� *IsNotLoggedIn+#	 [  �� 5F   
   $��7��5G      $��10 0 0 0q�5H      $��Register��5J      ��5I      �� *NavigateRegisterCommand+y�5K      �� *IsNotLoggedIn+#	 [  �� 5L   
   $��7��5M      $��10 0 0 0q�5N      $��Account��5P      ��5O      �� *NavigateAccountCommand+y�5Q      �� *
IsLoggedIn+#	 [  �� 5R   
   $��8��5S      $��10 0 0 0q�5T      $��Logout��5V      ��5U      �� *LogoutCommand+y�5W      �� *
IsLoggedIn+#	 [  5X      5Y      