from src.model.exceptions.exceptions import UserExistsError, UserInvalidError

class UserController:
    
    def __init__(self, service, view):
        self.service = service
        self.view = view

    def get_description(self):
        return 'New User'

    def process(self) -> None:
        self.view.process()
        user = self.view.get_user()

        try:
            self.service.add(user)
        except UserInvalidError as e:
            print(e)
        except UserExistsError as e:
            print(e)
