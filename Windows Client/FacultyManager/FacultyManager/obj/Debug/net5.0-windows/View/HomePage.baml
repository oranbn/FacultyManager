   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullE'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5      9!clr-namespace:FacultyManager.ViewFacultyManager.View  5      E'clr-namespace:FacultyManager.ComponentsFacultyManager.Components  5
      "    FacultyManager.View.HomePage   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      >
components'clr-namespace:FacultyManager.Components     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      3local!clr-namespace:FacultyManager.View     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      U QWpfAnimatedGif, Version=2.0.0.0, Culture=neutral, PublicKeyToken=9e7cd3b544a090dc,gif"http://wpfanimatedgif.codeplex.com  5	      6controls'clr-namespace:FacultyManager.Components  5
        � 	Resources  5          BooleanToVisibilityConverter��%   &	        �� 5   
   6'   5      .��5      � ��5   
   !� 5       !� Visual 5      �� 5       J� Opacity$	 0.7Q�6   "  WpfAnimatedGif.ImageBehavior   AnimatedSource$A ;/FacultyManager;component/View/BackGround/backgroundgif.gif��5         	AutoStart$
 True��5         RepeatBehavior$ 1��5      5       � RowDefinitions 5   
   �� 5      $
��auto �6   �� 5      $
��auto �6   �� 5      $��* �6   .+�5"   
   �� $��0��5#      $��28(�5$      $
��Home��5%      �� 5'   
   $��1��5(      $��0 20 0 0q�5)      ��5*      �� *WelcomeMessage+� 5+   
   $��2��6   $��Bottom=�6   $��10q�67   y�6C   �� *
IsLoggedIn+ �� 	Converter# [   � ColumnDefinitions 5,      �� 5-      $
��Auto �6#   �� 5.      $
��Auto �6#   �� 5/      $
��Auto �6#   .+�51      �� $��3��6   $	��750��6,   $	 0.5Q�68   $	��480��6F   $��Bottom=�6S   	 �� RadiusY$	 10��52      
 �� RadiusX$
 10��6%   
������� J� Effect 53      3  -System.Windows.Media.Effects.DropShadowEffect  54         
BlurRadius$ 15Q�6'      	Direction$ 0Q�67      RenderingBias$ Quality=�6E      ShadowDepth$ 1Q�6]      Color$ 	#FFBBBBBB��55   '   57      �� 58      -   5         ��Name$ MyList��58   }   $��0q�6   $	��750��6+   $	��480��67   $��0q�6D   
������ $��3��6i   ��6�   �� *CoursesModel.Courses+��59      �� 5:       �� 
TargetType" ��6   .�5;      ��  �� Property � �� �� Value
 ���� 67   �� 5<       � �� 5=      �� 5>   "    �� 
TargetType" ��62   .R�5?   &   �� 5@   #   5B      5C      ��5E      �� 5F      .R�5G      ��  J� InputBindings 5H      d� 5I   "    d� Gesture$ 
RightClicka�6/    �� Command 5J   /   �� *DataContext.Test+ �� RelativeSource ��  �� Mode$ FindAncestor=� �� AncestorType" >� �� Mode$ OneWay=� �� CommandParameter 5K   /   �� .+�5L      *   $FacultyManager.Components.CourseCard  $	��220��62   $	��200��6?   $��10q�6K   5M      5N      ��5P      �� 5Q      .R�5R      A�  A� Columns$ 3��6&   64   5S      5U      5V      5W      5X      