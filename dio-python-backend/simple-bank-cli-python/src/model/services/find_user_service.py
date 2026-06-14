
class FindUserService:

    def __init__(self, user_repository):
        self.user_repository = user_repository
    
    def find_all(self):
        return self.user_repository.find_all()
