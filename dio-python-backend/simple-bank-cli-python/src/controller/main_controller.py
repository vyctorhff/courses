class MainController:

    def __init__(self, menu_view, controllers):
        self.option = 0
        self.controllers = controllers
        self.menu_view = menu_view
    
    def process(self):
        self.menu_view.process(self.controllers)
        self.option = self.menu_view.get_option()

        if self.option < 0:
            return
        
        self.controllers[self.option].process()
    
    def is_exit(self) -> bool:
        return self.option == -1
