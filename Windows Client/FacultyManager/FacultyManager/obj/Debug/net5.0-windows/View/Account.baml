   M S B A M L   `   `   `  ����I  EFacultyManager, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null9!clr-namespace:FacultyManager.ViewFacultyManager.View  5      !    FacultyManager.View.Account   5      W SPresentationCore, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=5.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5      R NSystem.Xaml, Version=5.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5      @d2http://schemas.microsoft.com/expression/blend/2008     5      3local!clr-namespace:FacultyManager.View     5      .��5	   	   � ��5
   
   �� 5        �� ImageSource$Y  Spack://application:,,,/FacultyManager;component/View/BackGround/backgroundcolor.png��6    j� Stretch$ UniformToFill=�5   Z   .+�5   
   �� $
��Oran��6   $��Bottom=�6    
�������6Q   �� 5   
   $	��450��6   $	��450��6    $��Center=�6-    �� RadiusY$ 10��6I    �� RadiusX$ 10��6V   
������� J� Effect 5      3  -System.Windows.Media.Effects.DropShadowEffect  5         
BlurRadius$ 15Q�6#      	Direction$ 0Q�63      RenderingBias$ Quality=�6A      ShadowDepth$ 1Q�6Y   	   Color$	 	#FFBBBBBB��6i   5      � 5   
   $��Bottom=�6   $��0,130q�6*   $	��500��69   .+�5      �� $��Account Information��6   $��40q�62   $��Center=�6>   $	��Top=�6[   
�������$��18(�6�   �� 5      $��Center=�6   .+�5      �� $��Email:��6   $��Center=�6*   6I   �� 5      -   5        
 ��Name$
 EmailTextBox��5   (   $
��0,10q�6    �� 
IsReadOnly$
 True��6>   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6P   ��  �� Path$ Email� �� Mode$ TwoWay=� �� UpdateSourceTrigger$ PropertyChanged=�6�   �� 5      $��First Name:��6   $��Center=�6/   6N   �� 5      -   5        $
 FirstNameTextBox��5   (   $
��0,10q�6   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6B   �� $ 	FirstName�$ TwoWay=�$ PropertyChanged=�6�   �� 5      $��
Last Name:��6   $��Center=�6.   6M   �� 5      -   5        $
 LastNameTextBox��5   (   $
��0,10q�6   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6A   �� $ LastName�$ TwoWay=�$ PropertyChanged=�6�   �� 5      $	��ID:��6   $��Center=�6'   6F   �� 5      -   5        $
 	IDTextBox��5   (   $
��0,10q�6   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6;   �� $ IdNumber�$ TwoWay=�$ PropertyChanged=�6�   �� 5      $��Phone Number:��6   $��Center=�61   6P   �� 5       -   5        $
 PhoneNumberTextBox��5    (   $
��0,10q�6   $��Champagne & Limousines)�6�   $��18(�6�   $	��220��6�   ��6D   �� $ PhoneNumber�$ TwoWay=�$ PropertyChanged=�6�   �� 5!      
���  ��$��Center=�6W   $��40��6t   ��6   �� $ Message�$ OneWay=�6t   5"      �� 5#      $	��120��6   $��40��6!   $��15q�6-   $��Bottom=�69   $��Center=�6T      ButtonTemplate#��[  6�   ���#fff$��Update��6�   $��	UltraBold"�6�   ��6q   �� *UpdateAccountCommand+6�   5$      5%      5&      