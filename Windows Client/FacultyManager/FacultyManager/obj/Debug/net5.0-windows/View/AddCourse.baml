   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5   	   9!clr-namespace:FacultyManager.ViewFacultyManager.View  5   	   #    FacultyManager.View.AddCourse   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   >
components'clr-namespace:FacultyManager.Components     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   3local!clr-namespace:FacultyManager.View     5   	     J� InputBindings  5      �� 5   
   
 �� Key$	 Esc��66    �� Command 6   �� *CloseCommand+66   �� 5   
   $ Enter��6:    6   �� *AddCourseCommand+6:   .��5      � .+�5   
   �� $	��280��6   $	��200��6    $��Center=�6-    �� RadiusY$ 10��6H    �� RadiusX$ 10��6U   
������� J� Effect 5      3  -System.Windows.Media.Effects.DropShadowEffect  5         
BlurRadius$ 15Q�6#      	Direction$ 0Q�63      RenderingBias$ Quality=�6A   	   ShadowDepth$	 1Q�6Y   
   Color$
 	#FFBBBBBB��6i   5      � 5   
   $��Bottom=�6   $	��200��6*   ��5      �� 5       �� ImageSource$Y Spack://application:,,,/FacultyManager;component/View/BackGround/backgroundcolor.png��6    j� Stretch$ UniformToFill=�5   s   .+�5      �� $��
Add Course��6   $��Center=�6)   $	��Top=�6F   $��5q�6^   
����� �$��18(�6{   �� 5      $��Center=�6   .+�5      �� -   5         ��Name$ CourseNameTextBox��5   (   $
��0,10q�6   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6C   ��  �� Path$ 
CourseName� �� Mode$ TwoWay=� �� UpdateSourceTrigger$ PropertyChanged=�6�   �� 5       J� IsHitTestVisible$ False��6   $��Center=�65   $	��-30q�6R   $��Enter Course Name Here��6_   
���������5      �� 5       �� 
TargetType" ��6    .�5       ��  �� Property � y� �� Value$ 	Collapsed=�6;    �� Triggers 5!      �� 5"   "    �� Binding 6.   �� *Text+ �� ElementName$ CourseNameTextBox�� �� Value  6f   .a�5#   &   ��  � y�$ Visible=�6C   5$   #   5&      5(      �� 5)      -   5        $ GeneralInfoTextBox��5)   (   $
��0,10q�6   $��18(�6�   $	��220��6�   ��6D   �� $ GeneralInfo�$ TwoWay=�$ PropertyChanged=�.��6�   5*      �� 5+      $ False��6   $��Center=�65   $	��-30q�6R   $��Enter General Info Here��6_   
���������5,      �� 5-      " ��6    .�5.      ��  � y�$ 	Collapsed=�6;    5/      �� 50   "    6.   �� *Text+$ GeneralInfoTextBox��  6g   .a�51   &   ��  � y�$ Visible=�6C   52   #   54      56      �� 57      
���  ��$��Center=�6W   $��40��6t   ��6   �� $ Message�$ OneWay=�6t   58      �� 59      $	��110��6   $��40��6!   $��10q�6-   $��Bottom=�69   $��Center=�6T      ButtonTemplate#��[  6�   ���#fff$��
Add Course��6�   $��	UltraBold"�6�   ��6q   �� *AddCourseCommand+6�   5;      5<      5=      