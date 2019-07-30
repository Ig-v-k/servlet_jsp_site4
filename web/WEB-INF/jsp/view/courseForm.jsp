<template:basic htmlTitle="Create a Ticket" bodyTitle="Create a Ticket">
    <form method="POST" action="courses">
        <input type="hidden" name="action" value="create"/>
        Course name:<br/>
        <input type="text" name="courseName"><br/><br/>
        Professor name<br/>
        <input type="text" name="professor"><br/><br/>
        Student name:<br/>
        <input type="text" name="student"><br/><br/>
        <input type="submit" value="Submit"/>
    </form>
</template:basic>