// 这里模拟一些联系人数据
let contacts = [
    { id: 1, name: "张三", address: "北京", phone: "1234567890" },
    { id: 2, name: "李四", address: "上海", phone: "0987654321" }
];

// 加载现有联系人
function loadContacts() {
    const contactsList = document.getElementById("contacts");
    contactsList.innerHTML = "";
    contacts.forEach(contact => {
        const listItem = document.createElement("li");
        listItem.textContent = `${contact.name} - ${contact.address} - ${contact.phone}`;
        contactsList.appendChild(listItem);
    });
}

// 添加新联系人
function addContact() {
    const nameInput = document.getElementById("name");
    const addressInput = document.getElementById("address");
    const phoneInput = document.getElementById("phone");

    const newContact = {
        id: contacts.length + 1,
        name: nameInput.value,
        address: addressInput.value,
        phone: phoneInput.value
    };

    contacts.push(newContact);
    loadContacts();
    // 可以在这里添加逻辑以将数据发送到后端进行存储
}

// 初始加载联系人列表
loadContacts();
