from src.factory import FactoryController

factory = FactoryController()
main_controller = factory.create_main()

while (not main_controller.is_exit()):
    main_controller.process()
