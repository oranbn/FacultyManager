   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5      *    $FacultyManager.Components.CourseCard   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      9local'clr-namespace:FacultyManager.Components     5      .��5	      �� 
���iii�  �� CornerRadius$  5��6#   ��5
   
   �� 5      .�5      ��  �� Property � �� �� Value$ 3q�6;   6G   �� 5       � ��
 �2͚�6M    �� Triggers 5      P� 5       P� Property �@J�IsMouseOver P� Value$
 True��6<   . �5      ��  � ��$ 5q�6C   6O   �� 5       � ��
 � ��6T   �� 5       � ��$ DemiBold"�6A   6T   �� 5       � ��$ 14(�6?   6L   �� 5       � ��
 �����6Q   5      5      ._�5   
   �  � RowDefinitions 5      �� 5      $��* �6    �� 5      $
��Auto �6    .+�5      � $��0��6   ��5       �� 5!       �� ImageSource$N Hpack://application:,,,/FacultyManager;component/View/BackGround/book.jpg��6!    j� Stretch$ UniformToFill=�5"   l   5$      � 5%      $��1��6   $	��100��6    
����d�	 � ColumnDefinitions	 5&      �� 5'      $
��Auto �6'   �� 5(      $��* �6'   .+�5*      �� $��2��6   $��Center=�60   $	��5,5q�6M   $��16(�6v   $��Arial)�6�   ��6Z   �� *
CourseName+6�   �� 5,      $��0��6   $��
Teachers: ��6,   $
��Left=�6>   $��12(�6Y   $��Bottom=�6g   
���   �$��
ExtraBlack"�6�   �� 5-      $��1��6   $��5,0,0,0q�6,   $��Oran Ben Noon��6=   $��12(�6R   $
��Left=�6`   $��Bottom=�6{   
���   �$��
ExtraBlack"�6�   5.      5/      50      51      