   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5      9!clr-namespace:FacultyManager.ViewFacultyManager.View  5      &     FacultyManager.View.RemoveCourse   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      >
components'clr-namespace:FacultyManager.Components     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      3local!clr-namespace:FacultyManager.View     5        J� InputBindings  5      �� 5   
   
 �� Key$	 Esc��66    �� Command 6   �� *CloseCommand+66   .��5      � $	��150��6   $	��280��6    � ColumnDefinitions 5   
   �� 5      $��* �6   �� 5      $��* �6    � RowDefinitions 5   
   �� 5      $
��Auto �6   �� 5      $��* �6   .+�5   
   �� $��2��6   $��Remove Course��6$   $��Center=�6<   $	��Top=�6Y   $��5q�6q   
���-R��$��18(�5   $   �� 5   
   $��1��6   $��2��6!   ��65   ��  �� Path$ 
CourseName� �� StringFormat$0 *Are you sure you want to delete {0} course��5   P   �� 5   
   
���  ��$��Center=�6O   $��40��6l   ��6   �� $ Message� �� Mode$ OneWay=�6l   �� 5   
   $��1��6   $��0��6   $	��140��6.   $��40��6:   $��Bottom=�6F   $
��Left=�5      
������ 
�������
�����@�$��Cancel��5   =   $��	UltraBold"�6N   ��5   ,   �� *AddCourseCommand+5   N   �� 5   
   $��1��6   $��1��6   $	��140��6.   $��40��6:   $��Bottom=�6F   $
��Left=�5       
������ 
�������
���""��$��Remove��5!   =   $��	UltraBold"�6N   5"      5#      