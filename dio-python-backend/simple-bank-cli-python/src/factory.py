from src.model.domain.user import User
from src.model.domain.account import Account

from src.share.console import Console

from src.controller.main_controller import MainController
from src.controller.user_controller import UserController
from src.controller.exit_controller import ExitController
from src.controller.transference_controller import TransferenceController, WithdrawMoneyView
from src.controller.statment_controller import StatementController
from src.controller.select_user_controller import SelectUserController, SelectUserView

from src.view.menu_view import MenuView
from src.view.stament_view import StatmentView
from src.view.new_user_view import NewUserView

from src.model.repository.transaction_in_memory_repository import TransactionInMemoryRepository
from src.model.repository.user_in_memory_repository import UserInMemoryRepository

from src.model.services.stament_service import StatementService
from src.model.services.new_user_service import NewUserService
from src.model.services.find_user_service import FindUserService
from src.model.services.transaction_service import TransactionService

class FactoryRepository:

    def __init__(self, samples=True):
        self.user_repo = UserInMemoryRepository()
        self.transaction_repo = TransactionInMemoryRepository()

        if samples:
            self.add_user_sample()

    def get_user_repo(self):
        return self.user_repo
    
    def get_transaction_repo(self):
        return self.transaction_repo
    
    def add_user_sample(self):
        account = Account(1)
        
        self.user_repo.add(User('Rob', 'rob@email.com', 200.0, account))
        self.user_repo.add(User('Eli', 'eli@email.com', 100.0, account))
        self.user_repo.add(User('Nat', 'nat@email.com', 400.0, account))
        self.user_repo.add(User('Alb', 'alb@email.com', 600.0, account))
        self.user_repo.add(User('Kat', 'kat@email.com', 700.0, account))
        self.user_repo.add(User('Dan', 'dan@email.com', 300.0, account))

class FactoryService:

    def __init__(self):
        factory_repo = FactoryRepository()
        user_repo = factory_repo.get_user_repo()
        transaction_repo = factory_repo.get_transaction_repo()

        self.new_user = NewUserService(user_repo)
        self.select_user = FindUserService(user_repo)
        self.statment = StatementService(transaction_repo)
        self.transaction = TransactionService(transaction_repo)

    def get_new_user_service(self) -> NewUserService:
        return self.new_user

    def get_select_user_service(self) -> FindUserService:
        return self.select_user
    
    def get_statement_service(self) -> StatementService:
        return self.statment

    def get_transaction_service(self) -> TransactionService:
        return self.transaction

class FactoryController:

    def __init__(self):
        self.factory_service = FactoryService()
        self.console = Console()
    
    def create_main(self):
        controllers = [
            self.create_user(),
            self.create_withdraw(),
            self.create_statement(),
            self.create_exit()
        ]
        return MainController(MenuView(), controllers)

    def create_select_user(self):
        return SelectUserController(
            self.factory_service.get_select_user_service(),
            SelectUserView()
        )

    def create_statement(self):
        return StatementController(
            self.factory_service.get_select_user_service(),
            self.factory_service.get_statement_service(),
            StatmentView(),
            self.create_select_user()
        )

    def create_withdraw(self):
        return TransferenceController(
            self.factory_service.get_transaction_service(),
            self.create_select_user(),
            WithdrawMoneyView()
        )
    
    def create_user(self):
        return UserController(
            self.factory_service.get_new_user_service(),
            NewUserView()
        )
    
    def create_exit(self):
        return ExitController()
