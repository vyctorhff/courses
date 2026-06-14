from src.view.messages import MENU_MAIN_TITLE, MENU_ITEM, MENU_SELECT

class MenuView:
    
    def __init__(self, ):
        pass

    def process(self, controllers):
        print(MENU_MAIN_TITLE)

        for index, controller in enumerate(controllers):
            print(MENU_ITEM.format(index, controller.get_description()))

        self.option = input(MENU_SELECT)
    
    def get_option(self):
        return int(self.option)
